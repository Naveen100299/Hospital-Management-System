document.addEventListener("DOMContentLoaded", () => {

    const params = new URLSearchParams(window.location.search);
    const userId = params.get("user_id");
    const userName = params.get("name");
    const appointmentId=params.get("appointment_id");

    document.getElementById("user_id").value = userId;
    document.getElementById("user_name").value = userName;
    document.getElementById("appointment_id").value = appointmentId;

   
    fetch(`/HMS/prescription?user_id=${userId}`)
        .then(res => res.json())
        .then(data => {
            if (data) {
                document.getElementById("diagnosis").value = data.diagnosis || "";
                document.getElementById("medicines").value = data.medicines || "";
                document.getElementById("doctor_advice").value = data.doctor_advice || "";
            }
        })
        .catch(err => console.error("Load failed", err));

    
    const form = document.getElementById("prescriptionForm");

    form.addEventListener("submit", (e) => {
        e.preventDefault();

        const payload = {
            user_id: parseInt(userId),
            appointment_id:parseInt(appointmentId),
            diagnosis: document.getElementById("diagnosis").value.trim(),
            medicines: document.getElementById("medicines").value.trim(),
            doctor_advice: document.getElementById("doctor_advice").value.trim()
        };

        fetch("/HMS/prescription", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        })
        .then(res => res.json())
        .then(() => {
            alert("Prescription saved successfully");
            
        })
        .catch(() => alert("Something went wrong"));
    });
});
