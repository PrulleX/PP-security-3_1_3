const table = document.getElementById('tableBody') //получаем данные с файла user.html к div tableBody
const spann = document.getElementById('span') // получаем данные с файла user.html к див span , находится свепху с id span

fetch("http://localhost:8080/forUser") // просто делаем запрос по мапе , которая есть в контроллере
    .then((response) => {
        return response.json();
    })
    //внутренне содержимое тега tableBody из файла user.html
    .then((user) => {
        // обозначаем поля таблицы (вытаскиваем данные с бэка)
        let tr = `<tr><td>${user.id}</td>  
                  <td>${user.userName}</td>
                  <td>${user.surName}</td>
                  <td>${user.age}</td>
                  <td>${user.email}</td>
                  <td>${user.roles.map(role => role.name.substring(5))}</td></tr>`
        table.innerHTML = tr // выводм их в user.html

        spann.innerHTML = `<h5><b>${user.email}</b> with roles: ${user.roles.map(role => role.name.substring(5))}</h5>`
        // абсолютно тоже самое только со спаном который забрали из const spann

// проверка на совпадение ролей для таб панели
        let  roles = `${user.roles.map(role => role.name.toString())}`
        console.log(roles)
        if (roles.indexOf("ADMIN") === -1) {
            document.getElementById('admin').style.display = "none";
        }
    });