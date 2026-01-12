document.getElementById("logout").addEventListener("click",function(e){

e.preventDefault();




fetch("/HMS/logout")
.then(res=>res.json())
.then(data=>{
if(data.status){
 sessionStorage.clear();
window.location.href=data.message;
}
}).catch(err=>
console.error("logout failed:"+err)
);


});