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

function createAnswer() {
    let data = {
        'boardId' : $('#board_id').val(),
        'contents' : $('#answer_contents').val(),
        'writer' : $('#write').val()
    }

    $.ajax({
        type: 'POST',
        url: '/api/answer/',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            window.location.href = '/board/only/' + $('#board_id').val()
        },
        error: function (response) {
            alert("로그인 후 댓글 등록 가능합니다.")
            window.location.href = '/member/login'
        }
    })
}

function updateAnswer(id) {
    let answerid = id + '-answer-id';
    let writer = id + '-answer-writer';
    let contents = id + '-answer-contents';

    let data = {
        'answerId' : $('#'+answerid).val(),
        'contents' : $('#' + contents).val(),
        'writer' : $('#' + writer).val()
    }

    $.ajax({
        type: 'PUT',
        url: '/api/answer/',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            window.location.href = '/board/only/' + $('#board_id').val()
        }
    })
}

function deleteAnswer(id) {
    let answerid = id + '-answer-id';

    let data = {
        'answerId' : $('#'+answerid).val(),
    }

    $.ajax({
        type: 'DELETE',
        url: '/api/answer/',
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            window.location.href = '/board/only/' + $('#board_id').val()
        }
    })
}