fetch("/HMS/ViewupComingAppoinment")
  .then(res => res.json())
  .then(data => {
    const tbody = document.getElementById("appointmentBody");
    tbody.innerHTML = "";

    if (data.length === 0) {
      tbody.innerHTML = `<tr><td colspan="5">No upcoming appointments</td></tr>`;
      return;
    }

    data.forEach(a => {
      tbody.innerHTML += `
        <tr>
          <td>${a.user_id}</td>
          <td>${a.reason}</td>
          <td>${a.department}</td>
          <td>${a.appointmentDate}</td>
          <td style="font-weight:bold">8 AM</td>
          <td style="color:green;font-weight:bold">CONFIRMED</td>

        </tr>`;
    });
  });
