document.addEventListener("DOMContentLoaded", function () {

    fetch("/HMS/todayappointments")
        .then(function (res) {
            return res.json();
        })
        .then(function (data) {

            var tbody = document.getElementById("todayappointments");
            if (!tbody) return;

            tbody.innerHTML = "";

            if (!Array.isArray(data) || data.length === 0) {
                tbody.innerHTML =
                    "<tr><td colspan='6'>No appointments found</td></tr>";
                return;
            }

            data.forEach(function (app) {

                var statusText = app.status
                    ? app.status.toUpperCase()
                    : "UNKNOWN";

                var statusClass = statusText.toLowerCase();

                var appointmentDate = "-";
                if (app.appointmentDate && app.appointmentDate.time) {
                    appointmentDate = new Date(
                        app.appointmentDate.time
                    ).toLocaleDateString();
                }

                var row =
                    "<tr>" +
                    "<td>" + app.appoint_id + "</td>" +
                    "<td>" + app.user_id + "</td>" +
                    "<td>" + app.name + "</td>" +
                    "<td>" + app.reason + "</td>" +
                    "<td>" + app.appointmentDate + "</td>" +
                    "<td><span class='status " + statusClass + "'>" +
                    statusText +
                    "</span></td>" +
                    "</tr>";

                tbody.insertAdjacentHTML("beforeend", row);
            });
        })
        .catch(function (err) {
            console.error(err);
        });
});
