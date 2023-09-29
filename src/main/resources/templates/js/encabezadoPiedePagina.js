const navbarContainer = document.getElementById('navbar-container');

fetch('views/navbar.html')
    .then(response => response.text())
    .then(data => {
        navbarContainer.innerHTML = data;
    })
    .catch(error => {
        console.error('Error al cargar el encabezado:', error);
    });
