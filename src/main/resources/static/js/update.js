// (1) 회원정보 수정
function update(userId, event) {
	event.preventDefault();
	let data = $("#profileUpdate").serialize();

	$.ajax({
		url: `/api/user/${userId}`,
		type: "PUT",
		data: data,
		contentType: 'application/x-www-form-urlencoded; charset=utf-8',
		dataType: 'json',
		success: function(data) {
			//console.log(data);
			window.location.href = `/user/${userId}`;
		},
		error: function(error) {
			if(error.data == null){
				alert(error.responseJSON.message);
			}else{
				alert(JSON.stringify(error.responseJSON.data));
			}
		}
	});
}