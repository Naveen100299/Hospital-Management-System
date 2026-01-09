const form = document.getElementById("signinform");

form.addEventListener("submit", function (e) {
	const params = new URLSearchParams(window.location.search);
    const type = params.get("type");
    const loginRole = (type =="doctor") ? "DOCTOR" : "PATIENT";
    document.body.dataset.role = loginRole;
    e.preventDefault();   

    const formData = {};

    new FormData(this).forEach((value, key) => {
        formData[key] = value;
    });
    formData.role=loginRole;

    fetch("/HMS/signin", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(formData)
    })
    .then(response => response.json())
    .then(data => {
        if (data.status) {
            window.location.href =data.redirectUrl;
        } else {
            document.getElementById("message").innerHTML = data.message;
        }
    })
    .catch(err => {
        document.getElementById("message").innerHTML =
            `<span style="color:red">${err}</span>`;
    });
});
