fetch("/HMS/history")
  .then(res => res.json())
  .then(data => {
    const tbody = document.getElementById("history");
    tbody.innerHTML = "";

    if (!data || data.length === 0) {
      tbody.innerHTML = `
        <tr>
          <td colspan="5">No appointment history found</td>
        </tr>`;
      return;
    }

    data.forEach(a => {
 const statusText = a.status.toUpperCase(); 
                const statusClass = statusText.toLowerCase();

      tbody.innerHTML += `
        <tr>
          <td>${a.user_id}</td>
          <td>${a.appointmentDate}</td>
          <td>${a.department}</td>
          <td>${a.reason}</td>
          <td>
                            <span class="status ${statusClass}">
                                ${statusText}
                            </span>
                        </td>
        </tr>
      `;
    });
  })
  .catch(err => console.error("History fetch error:", err));
