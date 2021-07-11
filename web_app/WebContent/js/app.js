// Variables range to fetch rows from database
var start = 0;
var limit = 10;

// Holds serial_no of all the invoices to be deleted
var serial_nos = new Set();

function generateTable(data) {
    let table = document.getElementById("invoices");
    let tbody = document.createElement('tbody');

    data.forEach(invoice => {
        let row = document.createElement('tr');
        var i = invoice.serial_no
        row.setAttribute('id', 'row_' + i);
        let check_box = document.createElement('input');
        check_box.type = 'checkbox';
        check_box.setAttribute('class', 'check_box');
        check_box.setAttribute('id', 'serial_no_' + i);
        row.appendChild(check_box);
        for (const col in invoice) {
            if (col != "serial_no") {
                var td = document.createElement('td');
                td.appendChild(document.createTextNode(invoice[col]));
                row.appendChild(td);
            }
        }
        tbody.appendChild(row);
    });
    table.appendChild(tbody);
    highlightSelectedRow();
    reset();
}

function reset() {
    // Resets variables and some dom element states to default
    serial_nos = new Set()
    $('#add_btn').prop('disabled', false);
    $('#edit_btn').prop('disabled', true);
    $('#delete_btn').prop('disabled', true);
}

function highlightSelectedRow() {
    $(document).ready(function() {
        $('#invoices').find('input:checkbox[id^="serial_no_"]').click(function() {
            var serial_no = parseInt(this.id.slice(10));
            var isChecked = $(this).prop("checked");
            var $selectedRow = $(this).parent().closest('tr');
            selectedIndex = $selectedRow.index() + 1;
            var originalColor = '';

            if (selectedIndex % 2 == 0)
                originalColor = '#283A46';
            else
                originalColor = '#273D49CC';

            if (isChecked) {
                $selectedRow.css({
                    "background-color": "#2A5368",
                });
                serial_nos.add(serial_no);

            } else {
                $selectedRow.css({
                    "background-color": originalColor,
                });
                serial_nos.delete(serial_no);
            }
        });
    });
}

function getNextSet() {
    var table = document.getElementById('invoices');
    table.getElementsByTagName('tbody')[0].remove();
    start += 10;
    generateTable(getInvoiceList(start, limit))
}

function getPreviousSet() {
    if (start >= 10) {
        var table = document.getElementById('invoices');
        table.getElementsByTagName('tbody')[0].remove();
        start -= 10;
        generateTable(getInvoiceList(start, limit))
    }
}

function getInvoiceList(start, limit) {
    var url = 'http://localhost:8080/H2HBABBA2697/getInvoices?start=' + start + '&limit=' + limit;
    const xhttp = new XMLHttpRequest();
    xhttp.open("GET", url, false)
    xhttp.send()
    return JSON.parse(xhttp.responseText);
}

$("table").on('click', '.check_box', function() {
    var selectedInvoicesCount = serial_nos.size;
    if (selectedInvoicesCount > 0) {
        $('#add_btn').prop('disabled', true);
    } else {
        $('#add_btn').prop('disabled', false);
    }

    if (selectedInvoicesCount == 1) {
        $('#edit_btn').prop('disabled', false);
        activate_edit_invoice_window();
    } else {
        $('#edit_btn').prop('disabled', true);
    }

    if (selectedInvoicesCount > 0) {
        $('#delete_btn').prop('disabled', false);
        activate_delete_invoice_window();
    } else {
        $('#delete_btn').prop('disabled', true);
    }
});

function activate_add_invoice_window() {
    // Get Modal
    var add_btn_modal = document.getElementById("add");

    // Get the button that opens the modal
    var add_btn = document.getElementById("add_btn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    // When the user clicks the button, open the modal 
    add_btn.onclick = function() {
        add_btn_modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        add_btn_modal.style.display = "none";
    }
}

function activate_edit_invoice_window() {
    // Get modal
    var edit_btn_modal = document.getElementById("edit");

    // Get the button that opens the modal
    var edit_btn = document.getElementById("edit_btn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[1];

    // When the user clicks the button, open the modal 
    edit_btn.onclick = function() {
        edit_btn_modal.style.display = "block";
    }

    span.onclick = function() {
        edit_btn_modal.style.display = "none";
    }
}

function activate_delete_invoice_window() {
    var delete_btn_modal = document.getElementById("delete");

    // Get the button that opens the modal
    var delete_btn = document.getElementById("delete_btn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[2];

    // When the user clicks the button, open the modal 
    delete_btn.onclick = function() {
        delete_btn_modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        delete_btn_modal.style.display = "none";
    }

}

function addInvoice() {
    var form = document.getElementsByClassName("add_invoice")[0];
    var data = {
        customer_name: form.elements[0].value,
        customer_number: form.elements[1].value,
        invoice_id: form.elements[2].value,
        invoice_amount: parseFloat(form.elements[3].value),
        due_date: parseFloat(new Date(form.elements[4].value).getTime()),
        notes: form.elements[5].value
    }
    var jsonString = JSON.stringify(data);
    var http = new XMLHttpRequest();
    http.onreadystatechange = function() {
        if (this.readyState == 4) {
            var add_btn_modal = document.getElementById("add");
            add_btn_modal.style.display = "none";
            if (this.status == 200) {
                alert("Invoice added successfully");
                console.log("add", start, limit);
                var table = document.getElementById('invoices');
                table.getElementsByTagName('tbody')[0].remove();
                generateTable(getInvoiceList(start, limit));
            } else {
                alert("Operation unsuccessful");
            }

        }
    }
    var url = "http://localhost:8080/H2HBABBA2697/addInvoice";
    http.open('POST', url, true);
    http.send(jsonString);
}

function editInvoice() {
    var form = document.getElementsByClassName("edit_invoice")[0];
    var data = {
        invoice_amount: parseFloat(form.elements[0].value),
        notes: form.elements[1].value,
        serial_no: Array.from(serial_nos)[0]
    }
    var jsonString = JSON.stringify(data);
    var http = new XMLHttpRequest();
    http.onreadystatechange = function() {
        if (this.readyState == 4) {
            var edit_btn_modal = document.getElementById("edit");
            edit_btn_modal.style.display = "none";
            if (this.status == 200) {
                alert("Invoice edited successfully");
                console.log("edit", start, limit);
                var table = document.getElementById('invoices');
                table.getElementsByTagName('tbody')[0].remove();
                generateTable(getInvoiceList(start, limit));
            } else {
                alert("Operation unsuccessful");
            }

        }
    }
    var url = "http://localhost:8080/H2HBABBA2697/editInvoice";
    http.open('PUT', url, true);
    http.send(jsonString);

}


function deleteInvoices() {
    var jsonString = JSON.stringify(Array.from(serial_nos));
    var http = new XMLHttpRequest();
    http.onreadystatechange = function() {
        if (this.readyState == 4) {
            var delete_btn_modal = document.getElementById("delete");
            delete_btn_modal.style.display = "none";
            if (this.status == 200) {
                alert("Invoice deleted successfully");
                var table = document.getElementById('invoices');
                table.getElementsByTagName('tbody')[0].remove();
                generateTable(getInvoiceList(start, limit));
            } else {
                alert("Operation unsuccessful");
            }

        }
    }
    var url = "http://localhost:8080/H2HBABBA2697/deleteInvoices";
    http.open('DELETE', url, false);
    http.send(jsonString);
}



document.addEventListener('DOMContentLoaded', function() {
    // Generating Table
    generateTable(getInvoiceList(start, limit));

    activate_add_invoice_window();
});