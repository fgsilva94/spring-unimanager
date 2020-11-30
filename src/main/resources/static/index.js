document.getElementById("submit").addEventListener("click", async (e) => {
  e.preventDefault();
  try {
    const data = {
      name: document.getElementById("name").value,
      birthDate: document.getElementById("birthDate").value,
      gender: document.querySelector("input[name=gender]:checked").value,
    };

    let res = await $.ajax({
      url: "/api/students",
      data: JSON.stringify(data),
      method: "post",
      contentType: "application/json",
      dataType: "json",
    });

    document.getElementById("message").innerHTML = JSON.stringify(res);
  } catch (error) {
    console.log(error);
  }
});
