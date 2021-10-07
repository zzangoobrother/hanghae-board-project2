
alert("시작")
function deleteBoard(id) {
    let data = {
        'id' : $('#board_id').val()
    }

    $.ajax({
        type: 'DELETE',
        url: '/api/board/' + $('#board_id').val(),
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('삭제 완료');
            window.location.href = '/'
        }
    })
}