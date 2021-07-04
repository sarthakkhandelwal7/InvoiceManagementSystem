data = [{
        c_name: "Andrea James",
        c_number: 253532,
        invoice_number: 736546843516,
        amount: "122.87K",
        due_date: "23-jan-2021",
        predicted_date: "20-mar-2021",
        notes: ":)"
    },
    {
        c_name: "Andrea James",
        c_number: 253532,
        invoice_number: 736546843516,
        amount: "122.87K",
        due_date: "23-jan-2021",
        predicted_date: "20-mar-2021",
        notes: ":)"
    },
    {
        c_name: "Andrea James",
        c_number: 253532,
        invoice_number: 736546843516,
        amount: "122.87K",
        due_date: "23-jan-2021",
        predicted_date: "20-mar-2021",
        notes: ":)"
    },
    {
        c_name: "Andrea James",
        c_number: 253532,
        invoice_number: 736546843516,
        amount: "122.87K",
        due_date: "23-jan-2021",
        predicted_date: "20-mar-2021",
        notes: ":)"
    }
]

function generateTable(data) {
    let table = document.getElementById("invoices");
    let tbody = document.createElement('tbody');
    data.forEach(element => {
        let row = document.createElement('tr');
        let check_box = document.createElement('input');
        check_box.type = 'checkbox';
        check_box.setAttribute('class', 'check_box')
        row.appendChild(check_box)
        for (const col in element) {
            var td = document.createElement('td');
            td.appendChild(document.createTextNode(element[col]));
            row.appendChild(td);
        }
        tbody.appendChild(row);
        table.appendChild(tbody);
    });
}

// titles = ['Customer Name',	'Customer #', 'Invoice', 'Invoice Amount', 'Due Date', 'Predicted Payment Date', 'Notes']

generateTable(data);

// Get the modal
var add_btn_modal = document.getElementById("add");
var edit_btn_modal = document.getElementById("edit");
var delete_btn_modal = document.getElementById("delete");

// Get the button that opens the modal
var add_btn = document.getElementsByClassName("add_btn")[0];
var edit_btn = document.getElementsByClassName("edit_btn")[0];
var delete_btn = document.getElementsByClassName("delete_btn")[0];

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal 
add_btn.onclick = function() {
    add_btn_modal.style.display = "block";
}

// When the user clicks the button, open the modal 
edit_btn.onclick = function() {
    edit_btn_modal.style.display = "block";
}

// When the user clicks the button, open the modal 
delete_btn.onclick = function() {
    delete_btn_modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    add_btn_modal.style.display = "none";
    edit_btn_modal.style.display = "none";
    delete_btn_modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == add_btn_modal) {
        add_btn_modal.style.display = "none";
    } else if (event.target == edit_btn_modal) {
        edit_btn_modal.style.display = "none";
    } else if (event.target == delete_btn_modal) {
        delete_btn_modal.style.display = "none";
    }
}