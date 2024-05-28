import {BASE_URL} from "./reply.js";
import {renderReplies} from "./getReply.js";

// 서버에 댓글 삭제 요청하는 함수
export function deleteReply() {

    document.getElementById('replyData').onclick = e => {
        e.preventDefault();
        const rno = e.target.closest('#replyContent').dataset.replyId;
        if(!e.target.matches("#replyDelBtn")) return;




        fetch(`${BASE_URL}/${rno}`, {
            method: 'DELETE'
        })
            .then(res => res.json())
            .then(json => {
                renderReplies(json);
            });
    }

}