document.addEventListener('DOMContentLoaded', function () {
    const swiper = new Swiper('.swiper-container', {
        loop: true,
        slidesPerView: 3,
        spaceBetween: 20,
        navigation: {
            nextEl: '.next-ads',
            prevEl: '.prev-ads',
        },
    });
});

document.addEventListener('DOMContentLoaded', function () {
    const swiper = new Swiper('.swiper-places', {
        loop: true,
        slidesPerView: 4,
        spaceBetween: 20,
        navigation: {
            nextEl: '.swiper-button-next',
            prevEl: '.swiper-button-prev',
        },
        wrapperClass: 'swiper-main',
        slideClass: 'swiper-items',
    });
});

const airportsSwiper = new Swiper('.location-airport', {
    slidesPerView: 4,
    spaceBetween: 20,
    pagination: {
        el: '.airports-pagination',
        clickable: true,
    },
    navigation: {
        nextEl: '.airports-next',
        prevEl: '.airports-prev',
    },
    breakpoints: {
        768: {
            slidesPerView: 4,
            spaceBetween: 20,
        },
    },
});

function openForm(formId) {
    const forms = document.querySelectorAll('.form-popup');
    forms.forEach(form => {
        if (form.id === formId) {
            form.style.display = 'block';
        } else {
            form.style.display = 'none';
        }
    });
    document.getElementById('overlay').style.display = 'block';
}

function closeForm() {
    const forms = document.querySelectorAll('.form-popup');
    forms.forEach(form => {
        form.style.display = 'none';
    });
    document.getElementById('overlay').style.display = 'none';
}

const buttons = document.querySelectorAll('.service-options button');

buttons.forEach(button => {
    button.addEventListener('click', () => {
        buttons.forEach(btn => btn.classList.remove('active'));
        button.classList.add('active');
    });
});

function clearInput() {
    document.querySelector('.search-input').value = '';
}

function showDriverSearch() {
    document.getElementById('self-drive-search').style.display = 'none';
    document.getElementById('long-term-search').style.display = 'none';
    document.getElementById('driver-search').style.display = 'block';

}

function showSelfDriveSearch() {
    document.getElementById('self-drive-search').style.display = 'flex';
    document.getElementById('driver-search').style.display = 'none';
    document.getElementById('long-term-search').style.display = 'none';
}

function showlongTermSearch() {
    document.getElementById('self-drive-search').style.display = 'none';
    document.getElementById('driver-search').style.display = 'none';
    document.getElementById('long-term-search').style.display = 'flex';
}

function loadComponent(componentId, filePath) {
    fetch(filePath)
        .then(response => response.text())
        .then(data => {
            document.getElementById(componentId).innerHTML = data;
        });
}
function loadComponent(id, url) {
    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
            return response.text();
        })
        .then(data => {
            document.getElementById(id).innerHTML = data;
        })
        .catch(error => {
            console.error('Error loading component:', error);
        });
}
window.onload = function() {
    loadComponent('header', '/header.html');
    loadComponent('footer', '/footer.html');
}
// fetch('/header.html')
//     .then(response => response.text())
//     .then(html => {
//         document.querySelector('.headerCarOwner').innerHTML = html;
//     })
//     .catch(error => {
//         console.log('Lỗi khi tải footer.html:', error);
//     });
// fetch('/footer.html')
//     .then(response => response.text())
//     .then(html => {
//         document.querySelector('.footer').innerHTML = html;
//     })
//     .catch(error => {
//         console.log('Lỗi khi tải footer.html:', error);
//     });

function showUserInfo() {
    document.getElementById('actions').style.display = 'none';
    document.getElementById('info-user').style.display = 'flex';
}

document.getElementById('register').addEventListener('submit', function(event) {
    event.preventDefault();
    const isRegistered = true;
    if (isRegistered) {
        showUserInfo();
    }
});

document.getElementById('login').addEventListener('submit', function(event) {
    event.preventDefault();
    const isLoggedIn = true;
    if (isLoggedIn) {
        showUserInfo();
    }
});

