$(document).ready(function () {
    $('#firstCal').on('click', function (event){
        $('#Bar').collapse("toggle")
        $('#Foo').collapse("toggle")
        $('#secCal').removeClass('btn-primary').removeClass('active').removeAttr('disabled')
        $('#firstCal').addClass('btn-primary').addClass('active').attr('disabled',true)

    })//смена newUser на AdminPage
    $('#secCal').on('click', function (event){
        $('#Foo').collapse("toggle")
        $('#Bar').collapse("toggle")
         $('#firstCal').removeClass('btn-primary').removeClass('active').removeAttr('disabled')
         $('#secCal').addClass('btn-primary').addClass('active').attr('disabled',true)

    })

    $('#adminPanelBtn').on('click',function (event){
        $('#userPanel').collapse('toggle')
        $('#adminPanel').collapse('toggle')
        $('#userPanelBtn').removeClass('btn-primary').removeClass('active').removeAttr('disabled')
        $('#adminPanelBtn').addClass('btn-primary').addClass('active').attr('disabled',true)
    })

    $('#userPanelBtn').on('click',function (event){
        $('#adminPanel').collapse('toggle')
        $('#userPanel').collapse('toggle')
        $('#adminPanelBtn').removeClass('btn-primary').removeClass('active').removeAttr('disabled')
        $('#userPanelBtn').addClass('btn-primary').addClass('active').attr('disabled',true)
    })

    $('.table .eBtn').on('click', function (event) {
        event.preventDefault();
        const href = $(this).attr('href');

        $.get(href, function (user, status) {
            console.log(user, status);
            $(".myForm #id").val(user.id);
            $(".myForm #name").val(user.name)
            $(".myForm #lastName").val(user.lastName)
            $(".myForm #email").val(user.email)
            $(".myForm #password").val(user.password)
            $(".myForm #role").val(user.roles);
            $(".myForm #myFormPatch").attr('action', href)


        });
        $('.myForm #exampleModal').modal();


    });//на модалку edit

    $('.table .dBnt').on('click', function (event){
        event.preventDefault();
        const href=$(this).attr('href');

        $.get(href,function (user,status){
            console.log(user, status);
            $('.myForm #formLabel').contents()[0].nodeValue="Delete"
            $('.myForm #editBTN').val('Delete').removeClass('btn-primary').addClass('btn-danger')
            $(".myForm #id").val(user.id).prop('readOnly',true);
            $(".myForm #name").val(user.name).prop('readOnly',true);
            $(".myForm #lastName").val(user.lastName).attr('readonly',true);
            $(".myForm #email").val(user.email).attr('readOnly',true);
            $(".myForm #password").val(user.password).prop('readonly',true);
            $(".myForm #role").attr('readonly',true);
            $(".myForm #myFormPatch").attr('method','get').attr('action', href +'/delete')

        })
        $('.myForm #exampleModal').modal();
    })



});