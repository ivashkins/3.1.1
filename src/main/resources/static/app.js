$(document).ready(function () {
    loadTable();

    $('#firstCal').on('click', function (event) {
        $('#Bar').collapse("toggle")
        $('#Foo').collapse("toggle")
        $('#secCal').removeClass('btn-primary').removeClass('active').removeAttr('disabled')
        $('#firstCal').addClass('btn-primary').addClass('active').attr('disabled', true)

    })//смена newUser на AdminPage
    $('#secCal').on('click', function (event) {
        $('#Foo').collapse("toggle")
        $('#Bar').collapse("toggle")
        $('#firstCal').removeClass('btn-primary').removeClass('active').removeAttr('disabled')
        $('#secCal').addClass('btn-primary').addClass('active').attr('disabled', true)

    })
    $('#adminPanelBtn').on('click', function (event) {
        $('#userPanel').collapse('toggle')
        $('#adminPanel').collapse('toggle')
        $('#userPanelBtn').removeClass('btn-primary').removeClass('active').removeAttr('disabled')
        $('#adminPanelBtn').addClass('btn-primary').addClass('active').attr('disabled', true)
    })//смена adminPanel на UserPanel
    $('#userPanelBtn').on('click', function (event) {
        $('#adminPanel').collapse('toggle')
        $('#userPanel').collapse('toggle')
        $('#adminPanelBtn').removeClass('btn-primary').removeClass('active').removeAttr('disabled')
        $('#userPanelBtn').addClass('btn-primary').addClass('active').attr('disabled', true)
    })

    $('#myFormPatch').on('submit', function (e) {
        e.preventDefault()
        const action = $(this).attr('action')
        if (action.indexOf('delete') > 0) {

            $.ajax({
                url: action,
                dataType: 'text',
                type: 'GET',
                success: function (res) {
                   $('#'+res).remove();
                },
                error:function (res) {

                }
            })
        } else {
            $.ajax({
                url: action,
                dataType: 'text',
                type: 'PATCH',
                data: $('#myFormPatch').serialize(),
                success: function (res) {
                    var json = JSON.parse(res)
                    document.getElementById('id-name'+json.id).innerText = json.name
                    document.getElementById('id-lastName'+json.id).innerText = json.lastName
                    document.getElementById('id-email'+json.id).innerText = json.email
                    document.getElementById('id-roles'+json.id).innerText = json.roles
                },
                error: function (response) {
                    console.log(response)
                }
            })
        }
        $('#exampleModal').modal('hide')


    });//динамическое изменение данных в таблице
    $('#myPostForm').bind('submit', function (e) {//новый юзер добавление
        e.preventDefault()
        const action = $(this).attr('action')
        const email = this.email.value


        $.ajax({
            url: action,
            type: 'POST',
            dataType: 'text',
            data: $('#myPostForm').serialize(),
            success: function (res) {
                loadTable();
                $('#firstCal').trigger('click')
            },
            error: function (res) {
                console.log(res)
            }
        })//доделать динамическое добавление
    })


    $(document).on('click', '.eBtn', function (event) {
        event.preventDefault();
        const href = $(this).attr('href');
        const header = $('.myForm #formLabel').contents()[0].nodeValue;

        $.get(href, function (user, status) {
            $('.myForm #formLabel').contents()[0].nodeValue = "Edit"
            $('.myForm #submitMyFormBTN').val('Edit').removeClass('btn-danger').addClass('btn-primary')
            $(".myForm #id").val(user.id);
            $(".myForm #name").val(user.name).prop('readOnly', false)
            $(".myForm #lastName").val(user.lastName).prop('readOnly', false)
            $(".myForm #email").val(user.email).prop('readOnly', false)
            $(".myForm #password").val(user.password).prop('readOnly', false)
            $(".myForm #role").val(user.roles).attr('readOnly', false);
            $(".myForm #myFormPatch").attr('action', href)


        });
        $('.myForm #exampleModal').modal();


    });//на модалку edit

    $(document).on('click', '.dBtn', function (event) {
        event.preventDefault();
        const href = $(this).attr('href');

        $.get(href, function (user, status) {
            console.log(user, status);
            $('.myForm #formLabel').contents()[0].nodeValue = "Delete"
            $('.myForm #submitMyFormBTN').val('Delete').removeClass('btn-primary').addClass('btn-danger')
            $(".myForm #id").val(user.id).prop('readOnly', true);
            $(".myForm #name").val(user.name).prop('readOnly', true);
            $(".myForm #lastName").val(user.lastName).attr('readonly', true);
            $(".myForm #email").val(user.email).attr('readOnly', true);
            $(".myForm #password").val(user.password).prop('readonly', true);
            $(".myForm #role").attr('readonly', true);
            $(".myForm #myFormPatch").attr('method', 'get').attr('action', href + '/delete')

        })
        $('.myForm #exampleModal').modal();
    })

function loadTable(){
    fetch('/admin/users').then(//заполнение таблицы
        function (response) {
            if (response.status !== 200) {
                console.log('Looks like there was a problem. Status Code: ' +
                    response.status);
                return;
            }// Examine the text in the response
            response.json().then(function (data) {
                var tb = ""
                data.forEach((u) => {
                    tb += "<tr id=" + u.id + ">"
                    tb += "<td id='id-table'>" + u.id + "</td>"
                    tb += "<td id='id-name"+ u.id +"'>" + u.name + "</td>"
                    tb += "<td id='id-lastName"+ u.id +"'>" + u.lastName + "</td>"
                    tb += "<td id='id-email"+ u.id +"'>" + u.email + "</td>"
                    tb += "<td id='id-roles"+ u.id +"'>" + u.roles + "</td>"
                    tb += "<td>" + "<a class='btn btn-primary eBtn' href='/admin/" + u.id + "' id='eBtn'>Edit</a>" + "</td>"
                    tb += "<td>" + "<a class='btn btn-danger dBtn'  href='/admin/" + u.id + "'>Delete</a>" + "</td></tr>"

                })
                $('#tbody').html(tb)

            });
        }
    )
        .catch(function (err) {
            console.log('Fetch Error :-S', err);
        });

}

});