let dailyLimit = 0;
let totalExpenses = 0;
let expenses = [];

function setDailyLimit() {
    const limitInput = document.getElementById('dailyLimit');
    dailyLimit = parseFloat(limitInput.value);
    if (isNaN(dailyLimit) || dailyLimit <= 0) {
        alert('Please enter a valid daily spending limit.');
        return;
    }
    limitInput.disabled = true;
    document.getElementById('expenseTracker').style.display = 'block';
}

function addExpense() {
    const nameInput = document.getElementById('expenseName');
    const amountInput = document.getElementById('expenseAmount');
    const name = nameInput.value.trim();
    const amount = parseFloat(amountInput.value);
    if (name === '' || isNaN(amount) || amount <= 0) {
        alert('Please enter a valid expense name and amount.');
        return;
    }
    expenses.push({ name, amount });
    totalExpenses += amount;
    nameInput.value = '';
    amountInput.value = '';
    updateTotalExpenses();
    updateAllExpenses();
}

function updateTotalExpenses() {
    const totalExpensesElem = document.getElementById('totalExpenses');
    totalExpensesElem.textContent = `Total Expenses: $${totalExpenses.toFixed(2)}`;
    totalExpensesElem.style.color = totalExpenses > dailyLimit ? 'red' : 'green';
}

function viewTotalExpenses() {
    updateTotalExpenses();
    const message = totalExpenses > dailyLimit ? 'Exceed Your Daily Limit! Learn to Save Money.' : 'Expenses within your Daily Limit.';
    alert(message);
}

function viewAllExpenses() {
    const allExpensesElem = document.getElementById('allExpenses');
    allExpensesElem.innerHTML = '';
    expenses.forEach((expense, index) => {
        const li = document.createElement('li');
        li.textContent = `${index + 1}. ${expense.name} - $${expense.amount.toFixed(2)}`;
        allExpensesElem.appendChild(li);
    });
}

function deleteExpense() {
    const indexInput = document.getElementById('deleteIndex');
    const index = parseInt(indexInput.value);
    if (isNaN(index) || index < 1 || index > expenses.length) {
        alert('Invalid index. Please enter a valid index.');
        return;
    }
    const deletedExpense = expenses.splice(index - 1, 1)[0];
    totalExpenses -= deletedExpense.amount;
    indexInput.value = '';
    updateTotalExpenses();
    updateAllExpenses();
}

function exit() {
    if (confirm('Are you sure you want to exit?')) {
        window.close();
    }
}
