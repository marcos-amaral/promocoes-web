function copyToClip(element) {
    var $temp = $("<input>");
    $("body").append($temp);
    $temp.val($(element).text()).select();
    document.execCommand("copy");
    $temp.remove();
    alert('Copiado para área de transferência!');
}

function hideLoader() {
    $('#loading').hide();
    $('#formTable\\:tabelaId').removeClass('hidden');
    $('#formHistory\\:tabelaHistoryId').removeClass('hidden');
}

function showLoader() {
    $('#loading').show();
}

function closeConfirmDlg() {
    PF('confirmDlg').hide();
    PF('confirmDlgAprovar').hide();
    PF('newDtGmudDlg').hide();
    PF('tabelaCell').filter();
    hideLoader();
    try{
        PF('expiredGmudDlg').hide();
    } catch (e){
        
    }
}

//$(document).ready(hideLoader);

// Strongly recommended: Hide loader after 20 seconds, even if the page hasn't finished loading
setTimeout(hideLoader, 20 * 1000);

PrimeFaces.locales['pt_BR'] = {
    closeText: 'Fechar',
    prevText: 'Anterior',
    nextText: 'Próximo',
    currentText: 'Começo',
    monthNames: ['Janeiro', 'Fevereiro', 'Março', 'Abril', 'Maio', 'Junho', 'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'],
    monthNamesShort: ['Jan', 'Fev', 'Mar', 'Abr', 'Mai', 'Jun', 'Jul', 'Ago', 'Set', 'Out', 'Nov', 'Dez'],
    dayNames: ['Domingo', 'Segunda', 'Terça', 'Quarta', 'Quinta', 'Sexta', 'Sábado'],
    dayNamesShort: ['Dom', 'Seg', 'Ter', 'Qua', 'Qui', 'Sex', 'Sáb'],
    dayNamesMin: ['D', 'S', 'T', 'Q', 'Q', 'S', 'S'],
    weekHeader: 'Semana',
    firstDay: 1,
    isRTL: false,
    showMonthAfterYear: false,
    yearSuffix: '',
    timeOnlyTitle: 'Só Horas',
    timeText: 'Tempo',
    hourText: 'Hora',
    minuteText: 'Minuto',
    secondText: 'Segundo',
    ampm: false,
    month: 'Mês',
    week: 'Semana',
    day: 'Dia',
    allDayText: 'Todo Dia',
    messages: {//optional for Client Side Validation
        'javax.faces.component.UIInput.REQUIRED': '{0}: Erro de Validação: O valor é obrigatório.',
    }
};