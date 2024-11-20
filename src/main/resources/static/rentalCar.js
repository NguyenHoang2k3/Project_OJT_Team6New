document.addEventListener('DOMContentLoaded', () => {
    const chooseItems = document.querySelectorAll('.choose-item li');
    const infoContent = document.getElementById('info-content');
    const addCarContainer = document.getElementById('add-car-container');
    const addCarContainer1 = document.getElementById('add-car-container1');
    const orderList = document.getElementById('order-list');
    const chooseStatus = document.getElementById('choose-status');


    const savedItemId = localStorage.getItem('selectedItemId');

    chooseItems.forEach(item => {
        item.addEventListener('click', () => {
            chooseItems.forEach(i => i.classList.remove('choose-item-active'));
            item.classList.add('choose-item-active');

            infoContent.textContent = item.getAttribute('data-info');

            if (item.id === "xe-cua-toi") {
                addCarContainer.style.display = 'block';
                addCarContainer1.style.display = 'block';
                orderList.style.display = 'none';
                chooseStatus.style.display = 'none';
            } else if (item.id === "chuyen-cua-toi") {
                orderList.style.display = 'block';
                chooseStatus.style.display = 'block';
                addCarContainer.style.display = 'none';
                addCarContainer1.style.display = 'none';
            } else {
                addCarContainer.style.display = 'none';
                addCarContainer1.style.display = 'none';
                orderList.style.display = 'none';
                chooseStatus.style.display = 'none';
            }

            localStorage.setItem('selectedItemId', item.id);
        });
    });

    if (window.location.hash) {
        const targetElement = document.getElementById(window.location.hash.substring(1));
        if (targetElement) {
            targetElement.click();
        }
    } else if (savedItemId) {
        const savedItem = document.getElementById(savedItemId);
        if (savedItem) {
            savedItem.click();
        }
    }
});



