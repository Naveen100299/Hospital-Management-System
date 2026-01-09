document.addEventListener("DOMContentLoaded", () => {
    fetch("/HMS/profile") // <- match servlet mapping & context
        .then(response => response.json())
        .then(data => {
            
                document.getElementById("user_id").innerText ="REC"+ data.user_id;
                document.getElementById("name").innerText = data.name;
            
        })
        .catch(err => console.error("Error fetching patient info:", err));
});
