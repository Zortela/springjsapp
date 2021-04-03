const usersList = document.querySelector(".users-list");
const userPage = document.querySelector(".user-page");
const addUserForm = document.querySelector(".add-user-form");
const selectRolesNew = document.querySelector(".select-roles-new");
const selectRolesEdit = document.querySelector(".select-roles-edit");
const showActive = document.querySelector(".activeUser");
const url = "http://localhost:8080/admin/users";
const urlUser = 'http://localhost:8080/admin/users/active'

let output = "";
let outputRoles = "";
let outputActiveUser = "";
let outputUserTable = "";

// Checkbox with choosing roles
function checkRoles(id) {
    outputRoles = `<select class="form-select text-center col-3" size="2" multiple
                            aria-label="size 2 multiple select example" id="${id}">
                    <option id="ROLE_ADMIN" value="ROLE_ADMIN" selected>ADMIN</option>
                    <option id="ROLE_USER" value="ROLE_USER">USER</option>
                  </select>`
    return outputRoles;
}

selectRolesNew.innerHTML = checkRoles("selectNew");
selectRolesEdit.innerHTML = checkRoles("selectEdit");
getTable();

const allRoles = [{
    id: 1,
    name: "ROLE_ADMIN"
}, {
    id: 2,
    name: "ROLE_USER"
}]

// Fill the table
const renderUsers = (users) => {
    users.forEach(user => {
        output += `
                <tr>
                    <td>${user.id}</td>
                    <td>${user.username}</td>
                    <td>${user.name}</td>
                    <td>${user.surname}</td>
                    <td>${user.age}</td>
                    <td>${rolesToString(user.roles)}</td>
                    <td>
                        <button class='btn btn-warning' data-toggle='modal' onclick='editModal(${user.id})'>
                            Edit
                        </button>
                    </td>
                    <td>
                        <button class='btn btn-danger' data-toggle='modal' onclick='deleteModal(${user.id})'>
                            Delete
                        </button>
                    </td>
                </tr>
            `;
    });
    usersList.innerHTML = output;
}

// Get active user
// Method: GET

fetch(`${urlUser}`)
    .then(res => res.json())
    .then(data => {
        let result = "";
        result += data.username;
        result += " with roles: " + rolesToString(data.roles);
        outputActiveUser = `<a>${result}</a>`;

        outputUserTable = `
                    <tr>
                        <td>${data.id}</td>
                        <td>${data.username}</td>
                        <td>${data.name}</td>
                        <td>${data.surname}</td>
                        <td>${data.age}</td>
                        <td>${rolesToString(data.roles)}</td>
                    </tr>
                `;

        userPage.innerHTML = outputUserTable;
        showActive.innerHTML = outputActiveUser;
    })

// Get - Read users
// Method: GET
function getTable() {
    output = "";
    fetch(url)
        .then(res => res.json())
        .then(data => {
            renderUsers(data);
        });
}

// Creat - Insert new user
// Method: POST
addUserForm.addEventListener("submit", (e) => {
    e.preventDefault();
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/JSON"
        },
        body: JSON.stringify({
            username: document.getElementById("newUsername").value,
            password: document.getElementById("newPassword").value,
            name: document.getElementById("newName").value,
            surname: document.getElementById("newSurname").value,
            age: document.getElementById("newAge").value,
            roles: getCheckboxRoles("selectNew")
        })
    })
        .then(res => res.json())
        .then(data => {
            const dataArray = [];
            dataArray.push(data);
            renderUsers(dataArray);
        })
    formClear();
});

// Edit user
// method: POST
function editModal(id) {

    $("#editModal").modal('show')

    fetch(`${url}/` + id)
        .then(res => res.json())
        .then(data => {
            $("#editID").val(data.id)
            $("#editUsername").val(data.username)
            $("#editPassword").val(data.password)
            $("#editName").val(data.name)
            $("#editSurname").val(data.surname)
            $("#editAge").val(data.age)
            getCheckboxRoles("selectEdit")
        })
}

function editUser() {
    fetch(`${url}/` + $("#editID").val(), {
        method: "POST",
        headers: {
            "Content-Type": "application/JSON"
        },
        body: JSON.stringify({
            id: $("#editID").val(),
            username: $("#editUsername").val(),
            password: $("#editPassword").val(),
            name: $("#editName").val(),
            surname: $("#editSurname").val(),
            age: $("#editAge").val(),
            roles: getCheckboxRoles("selectEdit")
        })
    })
        .then(res => res.json())
        .then(getTable())
        .then($("#editModal").modal('hide'))


}


// Delete user
// method: DELETE
function deleteModal(id) {
    $("#deleteModal").modal("show")
    fetch(`${url}/` + id)
        .then(res => res.json())
        .then(data => {
            console.log(data.id)
            $("#deleteID").val(data.id)
            $("#deleteUsername").val(data.username)
            $("#deletePassword").val(data.password)
            $("#deleteName").val(data.name)
            $("#deleteSurname").val(data.surname)
            $("#deleteAge").val(data.age)
        })
}

function deleteUser() {
    fetch(`${url}/` + $("#deleteID").val(), {method: "DELETE"})
        .then(res => res.json())
        .then($("#deleteModal").modal('hide'))
        .then(getTable())

}

function rolesToString(roles) {
    let result = "";
    roles.forEach(role => {
        result += role.name.substring(5) + " ";
    });
    return result;
}

function getCheckboxRoles(id) {
    let result = [];
    let selectedRoles = Array.from(document.getElementById("" + id + '').options)
        .filter(option => option.selected)
        .map(option => option.value);
    selectedRoles.forEach(role => {
        if (role === allRoles[0].name) {
            result.push(allRoles[0]);
        }
        if (role === allRoles[1].name) {
            result.push(allRoles[1]);
        }
    })
    return result;
}

// Close editModal
function closeEditModal() {
    $('#editModal').modal('hide');
}

// Close deleteModal
function closeDeleteModal() {
    $('#deleteModal').modal('hide');
}

function formClear() {
    $("#newUsername").val("");
    $("#newPassword").val("");
    $("#newName").val("");
    $("#newSurname").val("");
    $("#newAge").val("");
}