<%--
  Created by IntelliJ IDEA.
  User: Ventotto
  Date: 2024/6/5
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=GBK" language="java" %>
<!DOCTYPE html>
<head>
    <title>��������ԤԼͳ��</title>
    <div style="text-align:center;">
        <h1 style="font-size: 75px">��������ԤԼͳ��</h1>
    </div>
</head>
<style>
    .grey1{
        background-color: lightgrey;
        font-size: 40px;
    }
    .grey2{
        background-color: gray;
        font-size: 40px;
    }
    table{
        border: 1px solid #ddd; /* ������ӱ߿������ֱ�� */
        width: 75%;
        border-collapse: separate;
        font-size: 40px;
    }
    table td {
        border: 1px solid #ddd; /* ������ӱ߿������ֵ�Ԫ�� */
        width: 50%; /* ������ֻ��һ���У����ʹ��Ԫ�������������� */
        font-size: 40px;
    }
    table thead {
        background-color: gray;
        width: 100%; /* ������ֻ��һ���У����ʹ��Ԫ�������������� */
        font-size: 40px;
    }
    input[type="text"] {
        width: 100%;
        height: 100%;
        font-size: 30px;
        padding: 0px;
    }
    input[type="date"] {
        width: 100%;
        height: 100%;
        font-size: 30px;
        padding: 0px;
    }
    input[type="number"] {
        width: 100%;
        height: 100%;
        font-size: 30px;
        padding: 0px;
    }
    option{
        width: 100%;
        height: 100%;
        font-size: 30px;
    }
    select{
        width: 100%;
        height: 100%;
        font-size: 30px;
    }
    .friend-item {
        margin-bottom: 10px;
    }
    .friend-item div {
        margin-bottom: 5px;
    }
    .friend-item button {
        display: block;
        margin-top: 5px;
    }
</style>
<body>
<div style="text-align:center;">
    <%-- ������ύ�û����� --%>
    <center>
<form action="../count-RezvPub" method="post">
    <table>
        <thead>
        <tr>
            <td colspan="2" style="text-align: center">ԤԼ��Ϣ</td>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="grey1">ԤԼ��</td>
            <td ><input type="text" name="serid" style="width: 98%" value="null"> </td>
        </tr>
        <tr>
            <td class="grey1">��������</td>
            <td ><input type="datetime-local" name="applytime" style="width: 98%" value="null"> </td>
        </tr>
        <tr>
            <td class="grey1">ԤԼУ��</td>
            <td>
                <input type="checkbox" name="campus" value="����У��"> ����У��<br>
                <input type="checkbox" name="campus" value="����У��"> ����У��<br>
                <input type="checkbox" name="campus" value="Ī��ɽУ��"> Ī��ɽУ��
            </td>
        </tr>

        <tr>
            <td rowspan="2" class="grey1">ԤԼ��Уʱ��</td>
            <td>
                <input type="datetime-local" name="intime" style="width: 98%" value="null"><br>
            </td>
        </tr>
        <tr>
            <td>
                <input type="datetime-local" name="outtime" style="width: 98%" value="null">
            </td>
        </tr>
        <tr>
            <td class="grey1">���ڵ�λ</td>
            <td><input type="text" name="unit" style="width: 98%" value="null"></td>
        </tr>
        <tr>
            <td class="grey1">������ʲ���</td>
            <td><select name="visitunit" style="width: 100%">
                <option value="education"> ����</option>
                <option value="security"> ������</option>
                <option value="finance"> �Ʋƴ�</option>
                <option value="committee"> ��ί</option>
            </select></td>
        </tr>
        <tr>
            <td class="grey1">������ʽӴ���</td>
            <td><input type="text" name="receptionist" style="width: 98%" value="null"></td>
        </tr>
        <tr>
            <td class="grey1">��������</td>
            <td><input type="text" name="reason" style="width: 98%" value="null"></td>
        </tr>
        <tr>
            <td class="grey1">���״̬</td>
            <td ><input type="text" name="status" style="width: 98%" value="null"> </td>
        </tr>
        </tbody>
    </table>

    <table  border="1" >
        <thead>
        <tr>
            <td colspan="2" style="text-align: center">�ÿ���Ϣ</td>
        </tr>
        </thead>
        <tbody>


        <tr>
            <td class="grey1">����</td>
            <td><input type="text" name="name" style="width: 98%" value="null"></td>
        </tr>
        <tr>
            <td class="grey1">���֤��</td>
            <td><input type="text" name="perid" placeholder="���������֤��" style="width: 98%" value="null"></td>
        </tr>
        <tr>
            <td class="grey1">�ֻ���</td>
            <td><input type="text" name="phoneNumber" placeholder="�������ֻ���" style="width: 98%" value="null"></td>
        </tr>
        <tr>
            <td class="grey1">��ͨ��ʽ</td>
            <td><select name="vehicle" style="width: 100%">
                <option value="walk"> ����</option>
                <option value="subway"> ����</option>
                <option value="bus"> ����</option>
                <option value="car"> ����</option>
            </select></td>
        </tr>
        <tr>
            <td class="grey1">���ƺ�</td>
            <td><input type="text" name="vname" style="width: 98%" value="null"> </td>
        </tr>
        <%--                <tr>--%>
        <%--                    <td class="grey1">��������</td>--%>
        <%--                    <td><input type="number" name="pnumber" style="width: 98%" value="null"> </td>--%>
        <%--                </tr>--%>
        <tr>
            <td class="grey1">��ͬ��Ա</td>
            <td>
                <div id="friendList">
                    <div class="friend-item">
                        <div><input type="text" name="Fri_name" placeholder="����" style="width: 98%;" value="null"></div>
                        <div><input type="text" name="Fri_perid" placeholder="���֤��" style="width: 98%;"value="null"></div>
                        <div><input type="text" name="Fri_phoneNumber" placeholder="�ֻ���" style="width: 98%;"value="null"></div>
                        <%--                                <button type="button" onclick="addFriend()">����</button>--%>
                    </div>
                </div>
            </td>
        </tr>
        </tbody>
    </table>
    <input type="submit" value="�ύ" style="font-size: 40px"/>
    <input type="reset" value="����" style="font-size: 40px"/>
</form>
    </center>
</div>
</body>
</html>
