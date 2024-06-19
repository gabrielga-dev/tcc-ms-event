UPDATE document_template
SET content = '<!DOCTYPE html>
<html>
<head>
    <title>Contrato para banda</title>
    <style>
        body {
            font-size: 12pt;
            font-family: Calibri;
            text-align: justify;
            width: 210mm;
            height: 297mm;
            line-height: 1;
        }

        .paginacao {
            padding-top: 2cm;
            padding-bottom: 2cm;
            padding-left: 3cm;
            padding-right: 1.5cm;
            width: 100%;
            height: 100%;
        }

        .text-center {
            text-align: center;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        .titulo {
            font-weight: bold;
            font-size: 20px;
        }

        .padding {
            padding-top: 5px;
            padding-bottom: 5px;
            padding-right: 5px;
            padding-left: 5px;
        }

        .col-10 {
            width: 100%;
        }

        .avatar {
            border-radius: 50%;
        }
    </style>
</head>
<body>
<h1 align="center">Contrato</h1>
<h4 align="center">Banda <span th:text="${bandName}"></span></h4>

<br /><br /><br />

<h4 align="center"><b>CONTRATO DE PRESTAÇÃO DE SERVIÇOS MUSICAIS</b></h4>
<p align="justify">
    <u><b>CONTRATANTE:</b></u> <span th:text="${contractorName}"></span>,
    inscrito no CPF sob nº <span th:text="${contractorCpf}"></span> e
    detentor do endereço eletrônico <span th:text="${contractorEmail}"></span>.
</p>

<br /><br /><br />

<p align="justify">
    <u><b>CONTRATADO:</b></u> <span th:text="${bandOwnerName}"></span>,
    inscrito(a) no CPF sob nº <span th:text="${bandOwnerCpf}"></span> e
    detentor do endereço eletrônico <span th:text="${bandOwnerEmail}"></span>.
</p>

<br/><br/><br/>

<p align="justify">
    Pelo presente instrumento particular de Contrato de Prestação de Serviços, as partes acima qualificadas têm entre si
    justas e avençadas o seguinte:
</p>

<br/><br/>

<h4 align="center"><b><u>CLÁUSULA PRIMEIRA – DO OBJETO</u></b></h4>
<br/><br/>
<p align="justify">
    O(A) <b>CONTRATANTE</b>, na busca do serviço musical para evento <span th:text="${eventName}"></span>,
    a realizar-se às <span th:text="${eventTime}"></span>,
    do dia <span th:text="${eventInFull}"></span>,
    no endereço <span th:text="${eventAddress}"></span>, firma com o <b>CONTRATADO(A)</b>, o(a) qual se obriga a prestar
    à CONTRATANTE,
    serviço profissional atinente às habilidades dos músicos no setor musical em diferentes esferas na medida do
    solicitado.

    <br/><br/>

    Parágrafo único – <b>O(S) MÚSICO(S)</b> prestará(ão) à CONTRATANTE no que diz respeito a organização, gestão e
    oferta de suas habilidades com o(s) INSTRUMENTO(S) pela importância de <span th:text="${quotePrice}"></span>.
</p>

<br/><br/><br/>

<h4 align="center"><b><u>CLÁUSULA SEGUNDA – DAS CONDIÇÕES DE EXECUÇÃO DOS SERVIÇOS</u></b></h4>

<br/><br/>
<p align="justify">
    O(A) <b>CONTRATANTE</b> deverá indicar o(a) <b>CONTRATADO(A)</b> – como prestador do(s) serviço(s) – por suas
    atividades na área musical, bem como sua responsabilidade na organização e realização das mesmas em qualquer evento
    que requira músicas, à partir do momento em que forem ofertados e disponibilizados os recursos permissíveis e capitais para
    tal, conforme será firmado na CLÁUSULA TERCEIRA.
</p>

<br/><br/><br/>

<h4 align="center"><b><u>CLÁUSULA TERCEIRA – DA REMUNERAÇÃO</u></b></h4>

<br/><br/>
<p align="justify">
    A <b>CONTRATANTE</b> é responsável por eventuais retenções de impostos e contribuições previstos na legislação
    tributária e previdenciária e pagará, ao(à) <b>CONTRATADO(A)</b>, a quantia total de R$ <span th:text="${quotePrice}"></span>.
    O pagamento será efetuado pelo(a) CONTRATANTE e deverão ser emitidos, pelo CONTRATADO(A), os respectivos recibos.
</p>

<br/><br/><br/>

<h4 align="center"><b><u>CLÁUSULA QUARTA – DO REAJUSTE DO PREÇO</u></b></h4>
<br/><br/>
<p align="justify">
    O preço estipulado na cláusula anterior poderá ser reajustado desde que hajam explicações claras para tal; desde que
    seja de total consciência e aceitação pelas partes envolvidas; e desde que seja formalizado por meio de uma cláusula
    de reajuste – a ser assinada por ambas as partes.
</p>
<br/><br/><br/>
<h4 align="center"><b><u>CLÁUSULA QUINTA – DA VIGÊNCIA</u></b></h4>
<br/><br/>
<p align="justify">
    O presente contrato é válido até a data em que será realizada o evento, passando a vigorar a partir
    da data de sua assinatura, podendo ser rescindido por qualquer uma das partes, desde que seja paga a importância de
    50% do valor total definido na CLAUSULA TERCEIRA, pelo tempo e trabalho investidos em função do evento tratado nesse
    contrato.
</p>

<br/><br/><br/>

<h4 align="center"><b><u>CLÁUSULA SEXTA – DA RESCISÃO</u></b></h4>

<br/><br/>

<p align="justify">
    O presente contrato poderá ser rescindido por qualquer uma das partes, mediante notificação a outra por escrito (ou
    qualquer outra forma, desde que documentada) com prazo mínimo de 60 dias de antecedência, ressalvada a hipótese da
    parte denunciante indenizar a outra do valor correspondente ao da prestação dos serviços referente ao período.
</p>

<br/><br/>

<p align="justify">
    Parágrafo 1º - O contrato também poderá ser rescindido em caso de violação de quaisquer das cláusulas deste
    contrato, pela parte prejudicada, mediante denúncia imediata, sem prejuízo de eventual indenização cabível.
</p>

<p align="justify">
    Parágrafo 2º - Qualquer tolerância das partes quanto ao descumprimento das cláusulas do presente contrato
    constituirá mera liberalidade, não configurando renúncia ou novação do contrato ou de suas cláusulas que poderão ser
    exigidos a qualquer tempo.
</p>

<br/><br/><br/>

<h4 align="center"><b><u>CLÁUSULA SÉTIMA – DO REGIME JURÍDICO</u></b></h4>

<br/><br/>

<p align="justify">
    As partes declaram não haver entre si vínculo empregatício, tendo o (a) <b>CONTRATADO (A)</b> plena autonomia na
    prestação dos serviços. O (a) <b>CONTRATADO (A)</b> responde exclusivamente por eventual imprudência, negligência,
    imperícia ou dolo na execução de serviços que venham a causar qualquer dano à <b>CONTRATANTE</b> ou a terceiros,
    devendo responder regressivamente caso a <b>CONTRATANTE</b> seja responsabilizada judicialmente por tais fatos,
    desde que haja a denunciação da lide, salvo no caso de conduta da própria <b>CONTRATANTE</b> contrária à orientação
    dada pelo (a) <b>CONTRATADO (A)</b>.
</p>

<br/><br/>

<p align="justify">
    Isso se aplica, de forma mais específica, aos casos de não cumprimento com o tratado firmado para a prestação de
    serviços. Ausência injustificada e sem remanejo por iniciativa do(a) <b>CONTRATADO (A)</b>, falha na execução em
    função de fatores advindos de imprudências podem ser enumerados como exemplificações de aplicabilidade do que se
    firma no primeiro parágrafo dessa cláusula.
</p>

<br/><br/><br/>

<b>LOCAL E DATA</b>: _________________________________________________________
<br/><br/>
<b>CONTRATANTE</b>: _________________________________________________________
<br/><br/>
<b>CONTRATADO</b>: _________________________________________________________
<br/><br/>
<b>TESTEMUNHA 1 (INFORMAR RG)</b>: _________________________________________________________
<br/><br/>
<b>TESTEMUNHA 2 (INFORMAR RG)</b>: _________________________________________________________

</body>
</html>
'
WHERE id = 1;
