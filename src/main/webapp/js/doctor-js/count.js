
document.addEventListener("DOMContentLoaded", () => {
fetch("/HMS/count")
  .then(res => res.json())
  .then(data => {
      document.getElementById("todayPending").innerText = data.pendingCount;
      document.getElementById("totalCompleted").innerText = data.completedCount;
  })
  .catch(err => console.error(err));
});