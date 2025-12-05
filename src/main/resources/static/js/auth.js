// Manejo de formularios de autenticación

// Form de Login
document.getElementById('loginForm')?.addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const formData = {
        email: document.getElementById('loginEmail').value,
        password: document.getElementById('loginPassword').value
    };
    
    try {
        const response = await fetch('/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });
        
        if (response.ok) {
            const data = await response.json();
            showNotification('¡Inicio de sesión exitoso!', 'success');
            
            // Guardar token si existe
            if (data.token) {
                localStorage.setItem('authToken', data.token);
            }
            
            // Cerrar modal
            const modal = bootstrap.Modal.getInstance(document.getElementById('loginModal'));
            modal.hide();
            
            // Redirigir o actualizar UI
            setTimeout(() => {
                window.location.reload();
            }, 1500);
        } else {
            const error = await response.json();
            showNotification(error.message || 'Error al iniciar sesión', 'error');
        }
    } catch (error) {
        console.error('Error:', error);
        showNotification('Error de conexión. Intenta nuevamente.', 'error');
    }
});

// Form de Registro
document.getElementById('registerForm')?.addEventListener('submit', async function(e) {
    e.preventDefault();
    
    const password = document.getElementById('registerPassword').value;
    const passwordConfirm = document.getElementById('registerPasswordConfirm').value;
    
    // Validar que las contraseñas coincidan
    if (password !== passwordConfirm) {
        showNotification('Las contraseñas no coinciden', 'error');
        return;
    }
    
    // Validar longitud de contraseña
    if (password.length < 6) {
        showNotification('La contraseña debe tener al menos 6 caracteres', 'error');
        return;
    }
    
    const formData = {
        nombre: document.getElementById('registerNombre').value,
        email: document.getElementById('registerEmail').value,
        password: password
    };
    
    try {
        const response = await fetch('/auth/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(formData)
        });
        
        if (response.ok) {
            const data = await response.json();
            showNotification('¡Registro exitoso! Ahora puedes iniciar sesión', 'success');
            
            // Cambiar a tab de login
            const loginTab = document.getElementById('login-tab');
            loginTab.click();
            
            // Limpiar formulario
            document.getElementById('registerForm').reset();
            
            // Pre-llenar email en login
            document.getElementById('loginEmail').value = formData.email;
        } else {
            const error = await response.json();
            showNotification(error.message || 'Error al registrarse', 'error');
        }
    } catch (error) {
        console.error('Error:', error);
        showNotification('Error de conexión. Intenta nuevamente.', 'error');
    }
});

// Función para mostrar notificaciones (ya existe en main.js pero la duplicamos por si acaso)
function showNotification(message, type = 'info') {
    const notification = document.createElement('div');
    notification.className = `alert alert-${type === 'success' ? 'success' : 'danger'} position-fixed top-0 end-0 m-3 alert-dismissible fade show`;
    notification.style.zIndex = '9999';
    notification.innerHTML = `
        <strong>${type === 'success' ? '✓ ¡Éxito!' : '✗ Error'}</strong> ${message}
        <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
    `;
    
    document.body.appendChild(notification);
    
    setTimeout(() => {
        notification.remove();
    }, 5000);
}

// Validación en tiempo real
document.getElementById('registerEmail')?.addEventListener('blur', function() {
    const email = this.value;
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    
    if (email && !emailRegex.test(email)) {
        this.classList.add('is-invalid');
    } else {
        this.classList.remove('is-invalid');
    }
});

// Mostrar/ocultar contraseña
function togglePasswordVisibility(inputId) {
    const input = document.getElementById(inputId);
    const type = input.getAttribute('type') === 'password' ? 'text' : 'password';
    input.setAttribute('type', type);
}

// Verificar si el usuario ya está logueado
window.addEventListener('load', () => {
    const token = localStorage.getItem('authToken');
    if (token) {
        // Cambiar el botón de login por el nombre del usuario
        // Esta funcionalidad se puede expandir
        console.log('Usuario autenticado');
    }
});
