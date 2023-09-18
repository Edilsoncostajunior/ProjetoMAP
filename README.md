<h1 align='center'>Marketplace</h1>

<div>
    <h2>
    Se trata de um sistema de marketplace simplificado, com base em Amazon, Americanas ou Mercado Livre realizado em equipe. Onde existem basicamente 2 tipos de usuário (cada um com uma visãodiferente do sistema):
        <p>
            <ul>
                <li>A loja, que irá se cadastrar para vender no sistema;</li>
                <li>O comprador, que poderá comprar os produtos oferecidos;</li>
            </ul>
            <p align='center'>
                <img src='loja_comprador.png' width='50%'/>
            </p>
        </p>
    </h2>
</div>

<h1>Requisistos Gerais</h1>
<div>
    <h2>
        <p>
            <ul>
                <li>Req_01: CRUD de lojas:
                    <ul> 
                        <li>
                            Atributos de loja: nome, e-mail, senha, CPF/CNPJ, endereço;
                        </li>
                        <li>
                            Deve ser possível: cadastrar, exibir, buscar, atualizar e remover loja, listar lojas;
                        </li>
                    </ul>
                </li>
                <p>
                    <li>Req_02: CRUD de Compradores:
                        <ul> 
                            <li>
                                Atributos de comprador: nome, e-mail, senha, CPF, endereço;
                            </li>
                            <li>
                                Deve ser possível: cadastrar, exibir, buscar, atualizar e remover comprador, listar compradores;
                            </li>
                        </ul>
                    </li>
                </p>
                <p>
                    <li>Req_03: CRUD de produtos a serem vendidos por cada loja:
                        <ul> 
                            <li>
                                Atributos de produtos: nome, valor, tipo (alguma categoria: livro, eletrônicos, roupas, etc.), quantidade, marca, descrição;
                            </li>
                            <li>
                                Deve ser possível: cadastrar, exibir, buscar, atualizar e remover produto, listar produtos da loja;
                            </li>
                            <li>
                                Quando a loja cadastra seus produtos, eles já ficam disponíveis para venda, ou seja, a compra já pode ser efetuada.
                            </li>
                        </ul>
                    </li>
                    <p>
                        <li>Req_04: O comprador possui um carrinho de compras:
                            <ul> 
                                <li>
                                    Deve conter a lista de produtos que o usuário deseja comprar.
                                </li>
                            </ul>
                        </li>
                    </p>
                    <p>
                        <li>Req_05: Realizar venda de produtos:
                            <ul> 
                                <li>
                                    Deve ser possível o comprador efetuar a compra dos produtos que estão no carrinho de compras. Não vamos entrar no detalhe da transação financeira.
                                </li>
                            </ul>
                        </li>
                    </p>
                    <p>
                        <li>Req_06: O comprador possui um histórico de pedidos/compras:
                            <ul> 
                                <li>
                                    Deve exibir todas as compras que o usuário já fez.
                                </li>
                            </ul>
                        </li>
                    </p>
                    <p>
                        <li>Req_07: O comprador pode avaliar a compra (produto que ele comprou e/ou vendedor), através de uma nota (de 1 a 5) e um comentário:
                            <ul> 
                                <li>
                                    Em consequência dessa avaliação, a loja pode apresentar um conceito (ruim, médio, bom, excelente).
                                </li>
                            </ul>
                        </li>
                    </p>
                    <p>
                        <li>Req_08: O comprador possui uma pontuação que lhe assegura vantagens, tipo frete grátis, por exemplo:
                            <ul> 
                                <li>
                                    Essa pontuação é incrementada a cada compra e avaliação de compra;
                                </li>
                                <li>
                                    Veja que isso deve impactar na hora da realização da venda.
                                </li>
                            </ul>
                        </li>
                    </p>
                </p>
            </ul>
        </p>
    </h2>
</div>

