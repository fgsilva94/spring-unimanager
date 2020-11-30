document.getElementById("submit").addEventListener("click", async (e) => {
  e.preventDefault();
  try {
    const num = document.getElementById("number").value;
    console.log(num);

    let res = await $.ajax({
      url: `/api/students/${num}`,
      method: "delete",
      contentType: "application/json",
      dataType: "json",
    });

    document.getElementById("message").innerHTML = `Student ${res.message}`;
  } catch (error) {
    console.log(error);
  }
});
