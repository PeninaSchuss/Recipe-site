document.addEventListener('DOMContentLoaded', function() {
    const currentPage = window.location.pathname;

    const menuItems = document.querySelectorAll('.classynav ul li');

    menuItems.forEach(function(item) {
        const link = item.querySelector('a');
        const href = link.getAttribute('href');

        if (href === currentPage || href + '/' === currentPage) {
            item.classList.add('active');
        } else {
            const dropdownItems = item.querySelectorAll('.dropdown li');

            dropdownItems.forEach(function(dropdownItem) {
                const dropdownLink = dropdownItem.querySelector('a');
                const dropdownHref = dropdownLink.getAttribute('href');

                if (dropdownHref === currentPage || dropdownHref + '/' === currentPage) {
                    item.classList.add('active');
                }
            });
        }
    });
});

$(document).ready(function() {
    $('#search-form').hide(); // Hide the form initially

    $('.search-btn').click(function() {
        $('#search-form').toggle(); // Toggle the visibility of the form on button click
    });

    $('.close-btn').click(function() {
        $('#search-form').hide(); // Hide the form when close button is clicked
    });
});