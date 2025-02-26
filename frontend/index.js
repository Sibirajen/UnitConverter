
const units = {
    length: ['millimeter', 'centimeter', 'meter', 'kilometer', 'inch', 'foot', 'yard', 'mile'],
    weight: ['milligram', 'gram', 'kilogram', 'ounce', 'pound'],
    temperature: ['Celsius', 'Fahrenheit', 'Kelvin']
};

let currentTab = 'length';

function showTab(tab) {
    document.querySelectorAll('.tabs a').forEach(el => el.classList.remove('active'));
    document.getElementById(`${tab}-tab`).classList.add('active');
    currentTab = tab;
    populateUnits(tab);
}

function populateUnits(type) {
    const fromUnit = document.getElementById('from-unit');
    const toUnit = document.getElementById('to-unit');
    fromUnit.innerHTML = '';
    toUnit.innerHTML = '';
    units[type].forEach(unit => {
        const formattedUnit = unit.charAt(0).toUpperCase() + unit.slice(1).toLowerCase();
        fromUnit.innerHTML += `<option value="${unit}">${formattedUnit}</option>`;
        toUnit.innerHTML += `<option value="${unit}">${formattedUnit}</option>`;
    });
    fromUnit.value = units[type][0];
    toUnit.value = units[type][1];
}

// Initialize
populateUnits('length');


document.getElementById('converter-form').addEventListener('submit', async function (e) {
    e.preventDefault();
    
    // Get form values
    const type = getActiveTabName();
    const value = parseFloat(document.getElementById('value').value);
    const fromUnit = document.getElementById('from-unit').value;
    const toUnit = document.getElementById('to-unit').value;

    // Prepare data for backend
    const requestData = {
        type: type,
        value: value,
        fromUnit: fromUnit,
        toUnit: toUnit
    };

    try {
        // Send data to backend
        const response = await fetch('http://localhost:8080/', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestData)
        });

        // Check if request was successful
        if (!response.ok) {
            throw new Error('Failed to convert units');
        }

        // Get the result from the server
        const responseData = await response.json();
        document.getElementById('result').textContent = `Result: ${responseData.result} ${toUnit}`;
    } catch (error) {
        document.getElementById('result').textContent = 'Error: ' + error.message;
    }
});

function getActiveTabName() {
    // Get all the tabs
    const tabs = document.querySelectorAll('.tabs a');
    for (const tab of tabs) {
        // Check which tab is actually visible or highlighted — customize the condition as needed
        if (window.getComputedStyle(tab).display !== 'none' && tab.classList.contains('active')) {
            return tab.getAttribute('name').toUpperCase();
        }
    }
    return null; // If no tab is active
}
