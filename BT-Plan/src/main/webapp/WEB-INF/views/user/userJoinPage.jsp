<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src='https://www.google.com/recaptcha/api.js'></script>
<script>
	$(document) .ready(function() {
		$("#test_btn") .click(function() {
			$.ajax({
				url : 'bt.user.model/VerifyRecaptcha',
				type : 'post',
				data : {recaptcha : $( "#g-recaptcha-response") .val() },
				success : function(data) {
					witch (data) {
						case 0:
							alert("자동 가입 방지 봇 통과");
							break;
						case 1:
							alert("자동 가입 방지 봇을 확인 한뒤 진행 해 주세요.");
							break;
						default:
							alert("자동 가입 방지 봇을 실행 하던 중 오류가 발생 했습니다. [Error bot Code :  + Number(data) + "]");
							break;
				}
			}
		});
	});
});
</script>

<script>
function DaumPostcode() {
	
    new daum.Postcode({
    	
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("Address3").value = extraAddr;
            
            } else {
                document.getElementById("Address3").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('postcode').value = data.zonecode;
            document.getElementById("address1").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("Address2").focus();
        }
    }).open();
}

	function popupOpen(){

	window.open('idCheck.BT','popup','scrollbars=yes,width=550,height=300,top=200,left=500');
		
	
	}
function passwordCheck(){
	var password = document.getElementById("password").value;
	var passwordCheck = document.getElementById("passwordCheck").value;
	
	if(passwordCheck==""){
		document.getElementById("passwordCheckText").innerHTML="";
	}else if(password!=passwordCheck){
		document.getElementById("passwordCheckText").innerHTML="비밀번호가 일치하지 않습니다.";
	}else {
		document.getElementById("passwordCheckText").innerHTML="비밀번호가 일치합니다.";
	}
}

var pw_passed = true;  // 추후 벨리데이션 처리시에 해당 인자값 확인을 위해

function fn_pw_check() {
    var pw = document.getElementById("pw").value; //비밀번호
    var pw2 = document.getElementById("pwCheck").value; // 확인 비밀번호
    var id = document.getElementById("Uuserid").value; // 아이디

    pw_passed = true;

    var pattern1 = /[0-9]/;
    var pattern2 = /[a-zA-Z]/;
    var pattern3 = /[~!@\#$%<>^&*]/;     // 원하는 특수문자 추가 제거
    var pw_msg = "";

	if(id.length == 0) {
		alert("아이디를 입력해주세요");
		return false;
	} else {
			//필요에따라 아이디 벨리데이션
	}
	
    if(pw.length == 0) {
		alert("비밀번호를 입력해주세요");
		return false;
	} else {
		if( pw  !=  pw2) {
			//alert("비밀번호가 일치하지 않습니다.");
			return false;
		}
	}

	if(!pattern1.test(pw)||!pattern2.test(pw)||!pattern3.test(pw)||pw.length<8||pw.length>50){
		alert("영문+숫자+특수기호 8자리 이상으로 구성하여야 합니다.");
		document.getElementById('pw').value = "";
        document.getElementById('pwCheck').value = "";
        return false;
	}          

    if(pw.indexOf(id) > -1) {
		alert("비밀번호는 ID를 포함할 수 없습니다.");
		document.getElementById('pw').value = "";
        document.getElementById('pwCheck').value = "";
        return false;
    }

    var SamePass_0 = 0; //동일문자 카운트
    var SamePass_1 = 0; //연속성(+) 카운드
    var SamePass_2 = 0; //연속성(-) 카운드

    for(var i=0; i < pw.length; i++) {
		var chr_pass_0;
		var chr_pass_1;
		var chr_pass_2;
		
		if(i >= 2) {
			chr_pass_0 = pw.charCodeAt(i-2);
			chr_pass_1 = pw.charCodeAt(i-1);
			chr_pass_2 = pw.charCodeAt(i);
             
			//동일문자 카운트
		    if((chr_pass_0 == chr_pass_1) && (chr_pass_1 == chr_pass_2)) {
				SamePass_0++;
			} else {
				SamePass_0 = 0;
			}
		
			//연속성(+) 카운드
		    if(chr_pass_0 - chr_pass_1 == 1 && chr_pass_1 - chr_pass_2 == 1) {
				SamePass_1++;
			} else {
				SamePass_1 = 0;
			}
		
			//연속성(-) 카운드
			if(chr_pass_0 - chr_pass_1 == -1 && chr_pass_1 - chr_pass_2 == -1) {
				SamePass_2++;
			} else {
				SamePass_2 = 0;
			}  
		}     
          
		if(SamePass_0 > 0) {
			alert("동일문자를 3자 이상 연속 입력할 수 없습니다.");
			document.getElementById('pw').value = "";
			document.getElementById('pwCheck').value = "";
			pw_passed=false;
		}

		if(SamePass_1 > 0 || SamePass_2 > 0 ) {
			alert("영문, 숫자는 3자 이상 연속 입력할 수 없습니다.");
			document.getElementById('pw').value = "";
			document.getElementById('pwCheck').value = "";
			pw_passed=false;
		} 
         
		if(!pw_passed) {             
			return false;
			break;
		}
	}
	return true;
}
function isSame(){
	var pw=document.all.Upassword.value;
	var confirmPW=document.all.passwordCheck.value;
	
	fn_pw_check();
	
	if(document.getElementById('pw').value!=''&&document.getElementById('pwCheck').value!=''){
		if(document.getElementById('pw').value==document.getElementById('pwCheck').value){
			document.getElementById('same').innerHTML='비밀번호가 일치합니다.';
			document.getElementById('same').style.color='blue';
		} else {
			document.getElementById('same').innerHTML='비밀번호가 일치하지 않습니다';
			document.getElementById('pwCheck').value = "";
			document.getElementById('pw').value = "";
			document.getElementById('same').style.color='red';
		}
	}
}
function email_change(){
	if(document.userJoin.Uemail_select.options[document.userJoin.Uemail_select.selectedIndex].value == '0'){
		document.userJoin.Uemail_input.disabled = true;
		document.userJoin.Uemail_input.value = "";
	}
	if(document.userJoin.Uemail_select.options[document.userJoin.Uemail_select.selectedIndex].value == '9'){
		document.userJoin.Uemail_input.disabled = false;
		document.userJoin.Uemail_input.value = "@";
		document.userJoin.Uemail_input.focus();
	} else{
		document.userJoin.Uemail_input.disabled = true;
		document.userJoin.Uemail_input.value = document.userJoin.Uemail_select.options[document.userJoin.Uemail_select.selectedIndex].value;
	}
}
function inputPhoneNumber(obj) {
    var number = obj.value.replace(/[^0-9]/g, "");
    var phone = "";

    if(number.length < 4) {
        return number;
    } else if(number.length < 7) {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3);
    } else if(number.length < 11) {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 3);
        phone += "-";
        phone += number.substr(6);
    } else {
        phone += number.substr(0, 3);
        phone += "-";
        phone += number.substr(3, 4);
        phone += "-";
        phone += number.substr(7);
    }
    obj.value = phone;
}
</script>
<script type="text/javascript"> /* 서브밋 전에 리캡챠 체크 여부 를 확인합니다. */ 
function FormSubmit() { 
	if (grecaptcha.getResponse() == ""){ 
		alert("리캡챠를 체크해야 합니다."); return false; 
		} else { 
			return true; 
			} 
	} 
		</script>
</head>
<style>

 table{
 
  border: 1px;
  margin: 0px auto;
 
 
 }

fieldset {
 
 		position:relative;
		text-align: center;
		margin: 0px auto;
 		
 }


</style>
<body>
<%@ include file="../header.jsp" %>
	<!-- Main -->
		<div id="featured">
		<section style="margin: 0px auto;">
<form name="userJoin" action="userJoin_ok.BT" method="post" onsubmit="return FormSubmit();">
<div style="text-align: center;" align="center"  class="container-fluid">
<fieldset style="width: 800px; align-content: center;" class="container-fluid">
				<legend style="font-size: 20px;">회원 가입</legend>
				
			<table style="margin-left: auto; margin-right: auto; border: 1px; text-align: left;" class="table table-striped">
				<tr>
					<th style="text-align: center;">아이디</th>
					<td><div class="col-sm-5"><input type="text" class="form-control" name="Uuserid" id="Uuserid" readonly ></div>&nbsp;
					<input type="button" class="btn btn-primary btn-xs" value="아이디 중복검사" onclick="window.open('idCheck.BT','popup','scrollbars=yes,width=270,height=300,top=200,left=500');"></td>
					<!--  
					<input type="button" value="ID Check" onclick="show()">
					<span id="idcheckmsg"></span>-->
					</td>
					
				</tr>
				<tr>
					<th style="text-align: center;">닉네임</th>
					<td><div class="col-sm-5"><input type="text" class="form-control" name="Unickname" readonly></div>&nbsp;
					<input type="button" class="btn btn-primary btn-xs" value="닉네임 중복검사" onclick="window.open('nickCheck.BT','popup','scrollbars=yes,width=270,height=300,top=200,left=500');">
					</td>
				</tr>
				
				<tr>
					<th style="text-align: center;">비밀번호</th>
					<td><div class="col-sm-5"><input type="password" class="form-control" name="Upassword" id="pw" required="required" onchange="isSame()"></div></td>
				</tr>	
				<tr>
					<th style="text-align: center;">비밀번호 확인</th>
					<td><div class="col-sm-5"><input type="password" class="form-control" name="passwordCheck" id="pwCheck" required="required" onchange="isSame()"></div><span id="same"></span></td>
					
				</tr>
				<tr>
					<th style="text-align: center;">이름</th>
					<td><div class="col-sm-5"><input type="text" class="form-control" name="Uname" required="required"></div></td>
				</tr>
				<tr>
					<th style="text-align: center;">성별</th>
					<td><div class="col-sm-5"><input type="radio"  name="Usex" value="Male"> 남성&nbsp; 
						<input type="radio" name="Usex" value="Female"> 여성</div></td>
				</tr>
				<tr>
					<th style="text-align: center;">E-mail</th>
					<td><div class="col-sm-4"><input type="text" class="form-control" name="Uemail_self" value="이메일" onfocus="this.value='';"></div>
				
					<div class="col-sm-4"><input type="text" class="form-control" name="Uemail_input" value="" disabled ></div>

						<div class="col-sm-3"><select name="Uemail_select" onchange="email_change()" class="form-control">
						
						    <option value="0" >선택하세요</option>
						    <option value="9">직접입력</option>
						    <option value="@naver.com">naver.com</option>
						    <option value="@gmail.com">gmail.com</option>
						    <option value="@hanmail.net">hanmail.net</option>
						    <option value="@nate.com">nate.com</option> 
						    <option value="@hotmail.com">hotmail.com</option> 
						
						   </select></div></td>
				</tr>
			
				<tr>
					<th style="text-align: center;">연락처</th>
					<td><div class="col-sm-5"><input type="text" class="form-control" name="Uphone" onKeyup="inputPhoneNumber(this);" maxlength="13" style="text-align:center;"/></div></td>
				</tr>
	
				   <tr>
                    <th style="text-align: center;">생년월일</th>
                    <td><div class="col-sm-3">
                    	<select name="Uyear" class="form-control" >
                    	
                    	<option value="">년도</option>
                    	
                    	<c:forEach  var="year" begin="1965" end="2020">
                    		
                    		<option value="${year }">${year }</option>
                    	
                    	</c:forEach>
           
                        </select></div> 
                        
          				<div class="col-sm-3"><select name="Umonth" class="form-control">
          				
          				<option value="">월</option>
          				
                    	<c:forEach  var="month" begin="1" end="12">
                    		
                    		<option value="${month }">${month }</option>
                    	
                    	</c:forEach>
           
                        </select></div>  
                        
                        <div class="col-sm-3"><select name="Udate" class="form-control">
                        
                        <option value="">일</option>
                        
                    	<c:forEach  var="date" begin="1" end="31">
                    		
                    		<option value="${date }">${date }</option>
                    	
                    	</c:forEach>
           
                        </select></div> 
                    </td>
                </tr>
                
               <tr>
					<th style="text-align: center;">질의</th>
					<td><div class="col-sm-5"><select class="form-control">
						<option>선택</option>
						<option>나의 보물 1호는?</option>
						<option>가장 기억에 남는 영화는?</option>
						<option>다녔던 초등학교의 이름은?</option>
						<option>지금 살고있는 곳은?</option>
					</select></div></td>
				</tr>
                	<tr>
					<th></th>
					<td><div class="col-sm-5"><input type="text"  class="form-control" name="Usecondary" required="required"></div></td>
				</tr>
				<tr>
					<th style="text-align: center;">주소</th>
					<td><div class="col-sm-5"><input type="text" class="form-control" id="postcode" placeholder="우편번호" name="Uzipcode" required="required"></div>
					<input type="button" class="btn btn-primary btn-xs" onclick="DaumPostcode()" value="우편번호 찾기">
					</td>
				<tr>
				<th></th>	
					<td>
					<div class="col-sm-5"><input type="text" id="address1" class="form-control" placeholder="주소" name="Uaddress1"></div>
					<div class="col-sm-5"><input type="text" id="Address2" class="form-control" placeholder="상세주소" name="Uaddress2"></div>
					<div class="col-sm-5"><input type="text" id="Address3" class="form-control" placeholder="참고항목" name=Uaddress3></div></td>
				<tr>	
					
				</tr>
				
				
				<tr>
					<th></th>
					<td><div class="g-recaptcha" data-sitekey="6Lfmm64UAAAAAMZPhmTDbPakyRJhZCizcH2rds82"></div></td>
					
					
				</tr>
				<tr>
					<td colspan="3" align="center">
					<input type="submit" class="btn btn-primary btn-ms" value="가입">
					<input type="reset" class="btn btn-primary btn-ms" value="재입력">
					<input type="button" class="btn btn-primary btn-ms" value="이전 페이지" onclick="location.href='index.BT'">
					</td>
				</tr>
			</table>
			</fieldset>
</div>
</form>
</section>
</div>
		
<%@ include file="../footer.jsp" %>
</body>
</html>