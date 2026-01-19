
const form = document.getElementById("signupform");

form.addEventListener("submit", function (e) {
    e.preventDefault();

    const formDataObj = {};
    new FormData(form).forEach((value, key) => {
        formDataObj[key] = value;
    });

    fetch("/HMS/receptionistSignup", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        credentials: "same-origin",
        body: JSON.stringify(formDataObj)
    })
    .then(res => res.json())
    .then(data => {
        if (data.status) {
            alert("created successfully. PATID:"+data.patientId);
                window.location.href = "../../html/receptionist/receptionistpanel.html";
            form.reset();
        } else {
            alert(data.message || "Failed");
        }
    })
    .catch(() => alert("Server error"));
});
