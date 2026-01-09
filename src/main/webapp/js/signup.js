const form = document.getElementById("signupform");

form.addEventListener("submit", function (e) {
    e.preventDefault();

    const formDataObj = {};

    new FormData(this).forEach((value, key) => {
        formDataObj[key] = value;
    });

    fetch("/HMS/signup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formDataObj)
    })
    .then(response =>response.json())
    .then(data => {
        const msg = document.getElementById("message");
        
        if (data.status === true) {
           alert("Account created")
           window.location.href = "../html/signin.html";
        } else {
            msg.innerHTML = `<span style="color:red">${data.message}</span>`;
        }
    })
    .catch(err => {
        document.getElementById("message").innerHTML =
            `<span style="color:red">${err.message}</span>`;
    });
});
