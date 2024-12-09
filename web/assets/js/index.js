document.addEventListener("DOMContentLoaded", () => {
            const buttons = document.querySelectorAll(".btn-group-toggle .btn");
            buttons.forEach((button) => {
                button.addEventListener("click", () => {
                    buttons.forEach((btn) => btn.classList.remove("active"));
                    button.classList.add("active");
                });
            });
        });