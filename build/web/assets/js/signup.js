/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

 function validateForm(event) {
                // Get password and confirm password values
                const password = document.getElementById("password").value;
                const confirmPassword = document.getElementById("confirmPassword").value;

                // Check if passwords match
                if (password !== confirmPassword) {
                    // Prevent form submission
                    event.preventDefault();

                    // Show an error message
                    alert("Passwords do not match. Please re-enter your passwords.");
                }
            }
            