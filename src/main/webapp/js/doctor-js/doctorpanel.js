
document.addEventListener("DOMContentLoaded", () => {
fetch("/HMS/profile")
  .then(res => res.json())
  .then(data => {
      document.getElementById("name").innerText = "DR."+data.name;
      document.getElementById("user_id").innerText = "DOC-"+data.user_id;
      document.getElementById("department").innerText = data.department;
      })
  .catch(err => console.error(err));
});