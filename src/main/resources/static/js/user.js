const userPage = document.querySelector(".user-page");
const showActive = document.querySelector(".activeUser");
const url = "http://localhost:8080/user/json";

let outputActiveUser = "";
let outputUserTable = "";

const allRoles = [{
    id: 1,
    name: "ROLE_ADMIN"
}, {
    id: 2,
    name: "ROLE_USER"
}]

// Get active user
// Method: GET
fetch(`${url}`)
    .then(res => res.json())
    .then(data => {
        let result = "";
        result += data.username;
        result += " with role: " + data.roles[0].name.substring(5);

        outputActiveUser = `<a>${result}</a>`;

        outputUserTable = `
                    <tr>
                        <td>${data.id}</td>
                        <td>${data.username}</td>
                        <td>${data.name}</td>
                        <td>${data.surname}</td>
                        <td>${data.age}</td>
                        <td>${data.roles[0].name.substring(5)}</td>
                    </tr>
                `;

        showActive.innerHTML = outputActiveUser;
        userPage.innerHTML = outputUserTable;
    })