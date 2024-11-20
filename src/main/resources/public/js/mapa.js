function copyToClipboard(button, lat, long) {
    const text = `Latitud: ${lat}, Longitud: ${long}`;
    navigator.clipboard.writeText(text).then(() => {
        const messageDiv = button.nextElementSibling; // Selecciona el div del mensaje
        messageDiv.style.display = 'block'; // Muestra el mensaje
        messageDiv.classList.add('visible'); // Añade la clase para estilos
        setTimeout(() => {
            messageDiv.style.display = 'none'; // Oculta el mensaje después de 3 segundos
            messageDiv.classList.remove('visible'); // Limpia la clase para evitar conflictos
        }, 3000);
    }).catch(err => {
        console.error('Error al copiar al portapapeles: ', err);
    });
}