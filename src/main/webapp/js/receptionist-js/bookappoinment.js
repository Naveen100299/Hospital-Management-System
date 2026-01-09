document.addEventListener("DOMContentLoaded", () => {

    const patidInput = document.getElementById("patid");
    const patientNameInput = document.getElementById("patientName");

    patidInput.addEventListener("blur", function () {

        const patid = this.value.trim();
        if (!patid) return;

        fetch(`/HMS/getPatientName?patid=${patid}`)
            .then(res => res.json())
            .then(data => {
                if (data.status) {
                    patientNameInput.value = data.message;
                } else {
                    patientNameInput.value = "";
                    alert("Patient not found");
                    window.location.reload();
                }
            })
            .catch(err => {
                console.error(err);
                alert("Error fetching patient details");
            });
    });


    // ===============================
    // B) AUTO-SET DEPARTMENT BY REASON
    // ===============================
    const form = document.getElementById("bookappointment");
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
            departmentSelect.value = "General_Medicine";
        }
    });


    form.addEventListener("submit", function (e) {
        e.preventDefault();


        const formData = {
            user_id: form.patid.value,
            reason: form.reason.value,
            department: form.department.value,
            appointmentDate: form.appointmentDate.value
        };

        fetch("/HMS/bookappoinment", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(formData)
        })
        .then(res => res.json())
        .then(data => {
            if (data.status) {
                alert(data.message);
                window.location.href = "../../html/receptionist/receptionistpanel.html";
            } else {
                alert(data.message);
               window.location.reload();

            }
        })
        .catch(err => {
            console.error(err);
            alert("Error booking appointment");
        });
    });

});
