document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('add-employee-form').addEventListener('submit', function(event) {
        event.preventDefault();
        addEmployee();
    });

    document.getElementById('update-employee-form').addEventListener('submit', function(event) {
        event.preventDefault();
        updateEmployee();
    });

    document.getElementById('delete-employee-form').addEventListener('submit', function(event) {
        event.preventDefault();
        deleteEmployee();
    });

    document.getElementById('add-menu-form').addEventListener('submit', function(event) {
        event.preventDefault();
        addItem();
    });

    document.getElementById('update-menu-form').addEventListener('submit', function(event) {
        event.preventDefault();
        updateItem();
    });

    document.getElementById('delete-menu-form').addEventListener('submit', function(event) {
        event.preventDefault();
        deleteItem();
    });

    document.getElementById('add-order-form').addEventListener('submit', function(event) {
        event.preventDefault();
        addOrder();
    });

    document.getElementById('delete-order-form').addEventListener('submit', function(event) {
        event.preventDefault();
        deleteOrder();
    });

    loadMenuItemsForOrder();
});

function showCategory(category) {
    document.querySelectorAll('.nav-section').forEach(section => section.style.display = 'none');
    document.querySelectorAll('.section').forEach(section => section.style.display = 'none');

    document.getElementById(`nav-${category}`).style.display = 'block';
}

function showSection(section, category) {
    document.querySelectorAll('.section').forEach(section => section.style.display = 'none');

    document.getElementById(`${category}-${section}`).style.display = 'block';
    if (section === 'list') {
        if (category === 'employees') {
            loadEmployees();
        } else if (category === 'menu') {
            loadMenu();
        } else if (category === 'orders') {
            loadOrders();
        }
    }
}

function loadEmployees() {
    fetch('http://localhost:8080/api/employees')
        .then(response => response.json())
        .then(data => {
            const employeesList = document.getElementById('employees');
            employeesList.innerHTML = '';
            data.forEach(employee => {
                const li = document.createElement('li');
                li.textContent = `${employee.empFName} ${employee.empLName} (${employee.empEmail}) - ${employee.empID}`;
                employeesList.appendChild(li);
            });
        });
}

function addEmployee() {
    const empFName = document.getElementById('empFName').value;
    const empLName = document.getElementById('empLName').value;
    const empEmail = document.getElementById('empEmail').value;
    const empPassword = document.getElementById('empPassword').value;
    const empRole = document.getElementById('empRole').value;

    const employee = {
        empFName: empFName,
        empLName: empLName,
        empEmail: empEmail,
        passwrd: empPassword,
        empRole: empRole
    };

    fetch('http://localhost:8080/api/employees', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(employee)
    }).then(response => {
        if (response.ok) {
            loadEmployees();
            document.getElementById('add-employee-form').reset();
            showSection('list', 'employees');
        } else {
            alert('Error adding employee');
        }
    });
}

function updateEmployee() {
    const empId = document.getElementById('update-empId').value;
    const empFName = document.getElementById('update-empFName').value;
    const empLName = document.getElementById('update-empLName').value;
    const empEmail = document.getElementById('update-empEmail').value;

    const params = new URLSearchParams();
    if (empFName) params.append('fname', empFName);
    if (empLName) params.append('lname', empLName);
    if (empEmail) params.append('email', empEmail);

    fetch(`http://localhost:8080/api/employees/${empId}?${params.toString()}`, {
        method: 'PUT',
    }).then(response => {
        if (response.ok) {
            loadEmployees();
            document.getElementById('update-employee-form').reset();
            showSection('list', 'employees');
        } else {
            alert('Error updating employee');
        }
    });
}

function deleteEmployee() {
    const empId = document.getElementById('delete-empId').value;

    fetch(`http://localhost:8080/api/employees/${empId}`, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            loadEmployees();
            document.getElementById('delete-employee-form').reset();
            showSection('list', 'employees');
        } else {
            alert('Error deleting employee');
        }
    });
}

function loadMenu() {
    fetch('http://localhost:8080/api/menu')
        .then(response => response.json())
        .then(data => {
            const menuList = document.getElementById('menu');
            menuList.innerHTML = '';
            data.forEach(item => {
                const li = document.createElement('li');
                li.textContent = `${item.itemName} - $${item.price.toFixed(2)}`;
                menuList.appendChild(li);
            });
        });
}

function addItem() {
    const itemName = document.getElementById('itemName').value;
    const price = parseFloat(document.getElementById('price').value);

    const menuItem = {
        itemName: itemName,
        price: price
    };

    fetch('http://localhost:8080/api/menu', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(menuItem)
    }).then(response => {
        if (response.ok) {
            loadMenu();
            document.getElementById('add-menu-form').reset();
            showSection('list', 'menu');
        } else {
            alert('Error adding item');
        }
    });
}

function updateItem() {
    const itemName = document.getElementById('update-itemName').value;
    const price = document.getElementById('update-price').value;

    const params = new URLSearchParams();
    if (price) params.append('price', price.toString());

    fetch(`http://localhost:8080/api/menu/${itemName}?${params.toString()}`, {
        method: 'PUT',
    }).then(response => {
        if (response.ok) {
            loadMenu();
            document.getElementById('update-menu-form').reset();
            showSection('list', 'menu');
        } else {
            alert('Error updating item');
        }
    });
}

function deleteItem() {
    const itemName = document.getElementById('delete-itemName').value;

    fetch(`http://localhost:8080/api/menu/${itemName}`, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            loadMenu();
            document.getElementById('delete-menu-form').reset();
            showSection('list', 'menu');
        } else {
            alert('Error deleting item');
        }
    });
}

function loadOrders() {
    fetch('http://localhost:8080/api/order')
        .then(response => response.json())
        .then(data => {
            const ordersList = document.getElementById('orders');
            ordersList.innerHTML = '';
            data.forEach(order => {
                const li = document.createElement('li');
                li.textContent = `Order ID: ${order.orderId}, Item: ${order.item}, Quantity: ${order.quantity}`;
                ordersList.appendChild(li);
            });
        });
}

function addOrder() {
    const item = document.getElementById('order-item').value;
    const quantity = parseInt(document.getElementById('order-quantity').value);

    const order = {
        item: item,
        quantity: quantity
    };

    fetch('http://localhost:8080/api/order', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(order)
    }).then(response => {
        if (response.ok) {
            loadOrders();
            document.getElementById('add-order-form').reset();
            showSection('list', 'orders');
        } else {
            alert('Error adding order');
        }
    });
}

function deleteOrder() {
    const orderId = document.getElementById('delete-orderId').value;

    fetch(`http://localhost:8080/api/order/${orderId}`, {
        method: 'DELETE'
    }).then(response => {
        if (response.ok) {
            loadOrders();
            document.getElementById('delete-order-form').reset();
            showSection('list', 'orders');
        } else {
            alert('Error deleting order');
        }
    });
}

function loadMenuItemsForOrder() {
    fetch('http://localhost:8080/api/menu')
        .then(response => response.json())
        .then(data => {
            const orderItemSelect = document.getElementById('order-item');
            orderItemSelect.innerHTML = '';
            data.forEach(item => {
                const option = document.createElement('option');
                option.value = item.itemName;
                option.textContent = item.itemName;
                orderItemSelect.appendChild(option);
            });
        });
}


