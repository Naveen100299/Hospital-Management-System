document.addEventListener("DOMContentLoaded", () => {

    const form = document.getElementById("bookappoinment");
    const reasonSelect = form.reason;
    const departmentSelect = form.department;

    const cardiologyReasons = [
        "Chest Pain",
        "High Blood Pressure",
        "Heart Palpitations",
        "Shortness of Breath",
        "Irregular Heartbeat"
    ];

    reasonSelect.addEventListener("change", () => {
        const reason = reasonSelect.value;

        if (!reason) {
            departmentSelect.value = "";
            return;
        }

        if (cardiologyReasons.includes(reason)) {
            departmentSelect.value = "Cardiology";
        } else {
            // Fever, Vomiting, Stomach Pain, etc.
            departmentSelect.value = "General_Medicine";
        }
    });


    form.addEventListener("submit", function (e) {
        e.preventDefault();

        const formData = {
            reason: form.reason.value.trim(),
            department: form.department.value,
            appointmentDate: form.appointmentDate.value
        };

        console.log("Sending data:", formData);

        fetch("/HMS/bookappoinment", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(formData)
        })
        .then(res => res.json())
        .then(data => {
            if (data.status) {
                alert(data.message);
                window.location.href = "patientpanel.html";
            } else {
                alert(data.message);
            }
        })
        .catch(err => {
            console.error(err);
            alert("Server error");
        });
    });
});
