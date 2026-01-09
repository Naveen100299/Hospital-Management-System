document.addEventListener("DOMContentLoaded", () => {

    fetch("/HMS/completed")
        .then(res => {

            if (res.status === 401) {
                window.location.href = "../html/login.html";
                return null;
            }

            if (!res.ok) {
                throw new Error("Failed to load appointments");
            }

            return res.json();
        })
        .then(data => {

            if (!data) return;

            const tbody = document.getElementById("completedbody");
            tbody.innerHTML = "";

            if (data.length === 0) {
                tbody.innerHTML = `
                    <tr>
                        <td colspan="5">No Completed found</td>
                    </tr>
                `;
                return;
            }

            data.forEach(app => {

                
                const statusText = app.status.toUpperCase(); // PENDING / COMPLETED
                const statusClass = statusText.toLowerCase();

                const row = `
                    <tr>
                        <td>${app.appoint_id}</td>
                        <td>${app.user_id}</td>
                        <td>${app.name}</td>
                        <td>${new Date(app.appointmentDate).toLocaleDateString("en-GB", {day: "2-digit",month: "short",year: "numeric"})}</td>

                        <td>${app.reason}</td>
                        <td>
                            <span class="status ${statusClass}">
                                ${statusText}
                            </span>
                             
                        </td>
               <td>
                   ${
          statusText === "COMPLETED"
          ? `<button class="btn"
        onclick="window.location.href='../doctor/prescription.html?user_id=${app.user_id}&name=${encodeURIComponent(app.name)}&appointment_id=${app.appoint_id}&from=completed'">
        Prescription
        </button>`
       : `<span>-</span>`
      }
     </td>
     </tr>
                `;

                tbody.insertAdjacentHTML("beforeend", row);
            });
        })
        .catch(err => {
            console.error("Error loading appointments:", err);
        });
});
