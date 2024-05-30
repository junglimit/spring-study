// 회원가입 입력 검증 처리

// 계정 입력 검증
const $idInput = document.getElementById('user_id');
let idFlag = false;
const $signupButton = document.getElementById('signup-btn'); // 회원가입 버튼

// 계정 중복검사 비동기 요청 보내기
async function fetchDuplicateCheck(idValue) {

    const res = await fetch(`http://localhost:8383/members/check?type=account&keyword=${idValue}`);
    const flag = await res.json();

    idFlag = flag;
}

// 비밀번호 확인 비동기 요청 보내기
let pwFlag = false;

async function checkPassword(passwordValue) {
    const $passwordCheck = document.getElementById('password_check').value;

    // 비밀번호가 확인란과 일치하는지 확인
    pwFlag = passwordValue === $passwordCheck;
}


$idInput.addEventListener('keyup', async (e) => {

    // 아이디 검사 정규표현식
    const accountPattern = /^[a-zA-Z0-9]{4,14}$/;

    // 입력값 읽어오기
    const idValue = $idInput.value;
    // console.log(idValue);

    // 검증 메시지를 입력할 span
    const $idChk = document.getElementById('idChk');

    if (idValue.trim() === '') {
        $idInput.style.borderColor = 'red';
        $idChk.innerHTML = '<b class="warning">[아이디는 필수값입니다.]</b>';
        $signupButton.disabled = true;
    } else if (!accountPattern.test(idValue)) {
        $idInput.style.borderColor = 'red';
        $idChk.innerHTML = '<b class="warning">[아이디는 4~14글자 사이 영문,숫자로 입력하세요]</b>';
        $signupButton.disabled = true;
    } else {

        // 아이디 중복검사
        await fetchDuplicateCheck(idValue);

        if (idFlag) {
            $idInput.style.borderColor = 'red';
            $idChk.innerHTML = '<b class="warning">[아이디가 중복되었습니다. 다른 아이디를 사용하세요!]</b>';
            $signupButton.disabled = true;
        } else {
            $idInput.style.borderColor = 'skyblue';
            $idChk.innerHTML = '<b class="success">[사용가능한 아이디입니다.]</b>';
            $signupButton.disabled = false;
        }
    }

});

// 비밀번호 검사
const $passwordInput = document.getElementById('password');


$passwordInput.addEventListener('keyup', async (e) => {
    const passwordPattern = /([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/;

    const passwordValue = $passwordInput.value;
    // console.log(passwordValue);

    const $passwordChk = document.getElementById('pwChk');
    if (passwordValue.trim() === '') {
        $passwordInput.style.borderColor = 'red';
        $passwordChk.innerHTML = '<b class="warning">[비밀번호는 필수값입니다.]</b>';
        $signupButton.disabled = true;
    } else if (!passwordPattern.test(passwordValue)) {
        $passwordInput.style.borderColor = 'red';
        $passwordChk.innerHTML = '<b class="warning">[비밀번호는 영문과 특수문자를 포함한 최소 8자로 입력하세요.]</b>';
        $signupButton.disabled = true;
    } else {
        $passwordChk.innerHTML = '';
        checkPassword(passwordValue);

    }


})
const $passwordCheck = document.getElementById('password_check');

$passwordCheck.addEventListener('keyup', async (e) => {
    const passwordValue = $passwordInput.value;
    await checkPassword(passwordValue);

const $passwordChk2 = document.getElementById('pwChk2');
if (pwFlag) {
    $passwordInput.style.borderColor = 'skyblue';
    $passwordChk2.innerHTML = '<b class="success">[사용가능한 비밀번호입니다.]</b>'
    $signupButton.disabled = false;
} else {
    $passwordInput.style.borderColor = 'red';
    $passwordChk2.innerHTML = '<b class="warning">[비밀번호가 일치해야합니다.]</b>';
    $signupButton.disabled = true;
}
})

// 이름 검증
const $usernameInput = document.getElementById('user_name');

$usernameInput.addEventListener('keyup', async (e) => {
    // 이름 검사 정규표현식
    const namePattern = /^[가-힣]+$/;

    const usernameValue = $usernameInput.value;
    const $usernameChk = document.getElementById('nameChk');
    if(usernameValue.trim() === '') {
        $usernameInput.style.borderColor = 'red';
        $usernameChk.innerHTML = '<b class="warning">[이름은 필수값입니다.]</b>';
        $signupButton.disabled = true;
    } else if(!namePattern.test(usernameValue)) {
        $usernameInput.style.borderColor = 'red';
        $usernameChk.innerHTML = '<b class="warning">[한글로 6자 이내로 입력하세요.]</b>';
        $signupButton.disabled = true;
    }else{
        $usernameInput.style.borderColor = 'skyblue';
        $usernameChk.innerHTML = '<b class="success">[사용가능한 이름입니다.]</b>';
        $signupButton.disabled = false;
    }

});

// 이메일 검증
const $userEmailInput = document.getElementById('user_email');
$userEmailInput.addEventListener('keyup', async (e) => {
    const emailPattern = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;
    const userEmailValue = $userEmailInput.value;

    const $emailChk = document.getElementById('emailChk');
    if(userEmailValue.trim() === '') {
        $usernameInput.style.borderColor = 'red';
        $emailChk.innerHTML = '<b class="warning">[이메일은 필수값입니다.]</b>';
        $signupButton.disabled = true;
    }else if(!emailPattern.test(userEmailValue)) {
        $usernameInput.style.borderColor = 'red';
        $emailChk.innerHTML = '<b class="warning">[올바른 이메일 양식을 작성하세요.]</b>';
        $signupButton.disabled = true;
    }else {
        $usernameInput.style.borderColor = 'skyblue';
        $emailChk.innerHTML = '<b class="success">[사용가능한 이메일입니다.]</b>';
        $signupButton.disabled = false;
    }
});


