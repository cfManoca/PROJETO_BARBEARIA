// Barbearia Beco 7 — comportamento de interface (menu lateral + modais)

document.addEventListener("DOMContentLoaded", function () {
    // Menu lateral no celular (vira uma barra superior com toggle)
    var botaoMenu = document.querySelector(".botao-menu");
    var menuLinks = document.querySelector(".menu-links");

    if (botaoMenu && menuLinks) {
        botaoMenu.addEventListener("click", function () {
            menuLinks.classList.toggle("aberto");
            var expandido = menuLinks.classList.contains("aberto");
            botaoMenu.setAttribute("aria-expanded", expandido);
        });
    }

    // Modais de confirmação (cancelamento / exclusão)
    // Um mesmo modal é reaproveitado por vários itens de uma lista: a cada
    // clique, o texto e a ação do formulário são reconfigurados para o
    // item clicado antes do modal ser exibido.
    document.querySelectorAll("[data-abrir-modal]").forEach(function (gatilho) {
        gatilho.addEventListener("click", function () {
            var modal = document.getElementById(gatilho.getAttribute("data-abrir-modal"));
            if (!modal) return;

            var form = modal.querySelector("form");

            // texto customizado por item (ex: nome do cliente/agendamento)
            var descricao = gatilho.getAttribute("data-descricao");
            var alvoTexto = modal.querySelector("[data-descricao-modal]");
            if (alvoTexto) {
                alvoTexto.textContent = descricao || alvoTexto.getAttribute("data-texto-original") || alvoTexto.textContent;
                if (!alvoTexto.getAttribute("data-texto-original")) {
                    alvoTexto.setAttribute("data-texto-original", alvoTexto.textContent);
                }
            }

            // action do formulário aponta para o item certo (ex: /clientes/7)
            var acao = gatilho.getAttribute("data-form-action");
            if (form) form.setAttribute("action", acao || "#");

            modal.classList.add("aberto");
            var primeiroFoco = modal.querySelector(".caixa-modal");
            if (primeiroFoco) primeiroFoco.setAttribute("tabindex", "-1");
            if (primeiroFoco) primeiroFoco.focus();
        });
    });

    function fecharModal(modal) {
        modal.classList.remove("aberto");
    }

    document.querySelectorAll("[data-fechar-modal]").forEach(function (botao) {
        botao.addEventListener("click", function () {
            fecharModal(botao.closest(".sobreposicao-modal"));
        });
    });

    document.querySelectorAll(".sobreposicao-modal").forEach(function (modal) {
        modal.addEventListener("click", function (evento) {
            if (evento.target === modal) fecharModal(modal);
        });
    });

    // Fecha o modal aberto com a tecla Esc
    document.addEventListener("keydown", function (evento) {
        if (evento.key !== "Escape") return;
        document.querySelectorAll(".sobreposicao-modal.aberto").forEach(fecharModal);
    });

    // Seleção de horário na tela de agendamento
    var camposHorario = document.querySelectorAll("input[name='horario']");
    document.querySelectorAll(".horario-opcao").forEach(function (item) {
        item.addEventListener("click", function () {
            document.querySelectorAll(".horario-opcao").forEach(function (h) {
                h.classList.remove("selecionado");
            });
            item.classList.add("selecionado");
            camposHorario.forEach(function (campo) {
                campo.value = item.textContent.trim();
            });
        });
    });
});
