
// =========== 전역 변수 ===========
const BASE_URL = 'http://localhost:8383/api/v1/replies'
const bno = document.getElementById('wrap').dataset.bno; // 게시물 글 번호
console.log('글번호: ',bno)

// =========== 함수 정의 ===========
function renderReplies(replies) {
    // 댓글 목록 렌더링
    document.getElementById('replyCnt').textContent = replies.length

    // 댓글 목록 렌더링
    let tag = '';
    if(replies && replies.length > 0){

        replies.forEach(({reply_no, writer, text, createAt}) => {
            tag += `
    <div id='replyContent' class='card-body' data-reply-id='${reply_no}'>
        <div class='row user-block'>
            <span class='col-md-3'>
                <b>${writer}</b>
            </span>
            <span class='offset-md-6 col-md-3 text-right'><b>${createAt}</b></span>
        </div><br>
        <div class='row'>
            <div class='col-md-9'>${text}</div>
            <div class='col-md-3 text-right'>
                <a id='replyModBtn' class='btn btn-sm btn-outline-dark' data-bs-toggle='modal' data-bs-target='#replyModifyModal'>수정</a>&nbsp;
                <a id='replyDelBtn' class='btn btn-sm btn-outline-dark' href='#'>삭제</a>
            </div>
        </div>
    </div>
    `;
        });

    }else{
        tag = `<div id='replyContent' class='card-body'>댓글이 아직 없습니다! ㅠㅠ</div>`
    }
    document.getElementById('replyData').innerHTML = tag;
}
async function fetchReplies () {
    const res = await fetch(`${BASE_URL}/${bno}`);
    const replies = await res.json();
    // console.log(replies)

renderReplies(replies);
}

// 댓글 등록하는 함수
function addReplies() {

    const $newReplyWriter = document.getElementById('newReplyWriter');
    const $newReplyText = document.getElementById('newReplyText');
    document.getElementById('replyAddBtn').onclick = e => {
        const requestInfo = {
            method: 'POST',
            headers: {
                'content-type': 'application/json',
            },
            body: JSON.stringify({
                author: $newReplyWriter.value,
                text: $newReplyText.value,
                bno: document.getElementById('wrap').dataset.bno
            })
        }
         fetch(`${BASE_URL}`, requestInfo)
             .then(res => res.json())
             .then(json => {
                 renderReplies(json);
                 $newReplyWriter.value = '';
                 $newReplyText.value = '';
             });
    }
}


// =========== 실행 코드 ===========

// 댓글 목록 서버에서 불러오기
fetchReplies();

// 댓글 추가하기
addReplies();