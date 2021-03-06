import { HttpService } from 'app/services/http.service';
import { Usuario } from './../models/usuario';
import { EstruturaJson } from 'app/models/estrutura-json';
import { HttpControl } from './../models/http-control';
import { DesafioHotmartAppComponent } from './../app.component';
import { Http, Headers, RequestOptions, Response, RequestMethod } from '@angular/http';
import { Injectable } from "@angular/core";
import 'rxjs/add/operator/map';

import { Credentials } from './../models/credentials';
import { TipoRetornoEnum } from 'app/enum/tipo-retorno-enum';
import { UsuarioService } from 'app/services/usuario.service';

@Injectable()
export class AuthenticationService{

    constructor(private httpService: HttpService, private usuarioService: UsuarioService){}

    public login(credentials: Credentials): Promise<EstruturaJson> {
        
        return new Promise<EstruturaJson>((resolve, reject) => {

            this.httpService.realizarRequisicaoHttp(new HttpControl(DesafioHotmartAppComponent.API_URL + "/auth/user", RequestMethod.Get, null, this.createHeaders(credentials)))
            .then(estruturaJson => {
                
                if(estruturaJson){

                    let usuario = estruturaJson.voReturn as Usuario;
                    
                    if (usuario) {
                        
                        localStorage.removeItem('isSocketConnect');
                        localStorage.removeItem('credentialsSocketConnect');
                        localStorage.setItem('currentUser', JSON.stringify(usuario));
        
                    }
        
                }

                resolve(estruturaJson);

            });
       
        });         

    }

    public register(usuario: Usuario): Promise<EstruturaJson>{

        return new Promise<EstruturaJson>((resolve, reject) => {

            this.httpService.realizarRequisicaoHttp(new HttpControl(DesafioHotmartAppComponent.API_URL + "/auth/register", RequestMethod.Post, usuario)).then((estruturaJson: EstruturaJson) => {

                if(estruturaJson){

                    let usuario = estruturaJson.voReturn as Usuario;
                    
                    if (usuario) {
                        
                        localStorage.removeItem('isSocketConnect');
                        localStorage.removeItem('credentialsSocketConnect');
                        localStorage.setItem('currentUser', JSON.stringify(usuario));
        
                    }
        
                }

                resolve(estruturaJson);
                
            });

        });        

    }

    public logOut() {
        
        return new Promise<EstruturaJson>((resolve, reject) => {

            this.httpService.realizarRequisicaoHttp(new HttpControl(DesafioHotmartAppComponent.API_URL + "/logout", RequestMethod.Post, {}))
            .then((estruturaJson: EstruturaJson) => {

                let idUsuarioLogado: number = this.usuarioService.getIdUsuarioLogado();

                localStorage.removeItem('currentUser');
                localStorage.removeItem('isSocketConnect');
                localStorage.removeItem('credentialsSocketConnect');

                this.httpService.realizarRequisicaoHttp(new HttpControl(DesafioHotmartAppComponent.API_URL + "/user/atualizarStatusConectadoUsuario/" + idUsuarioLogado + "/false", RequestMethod.Post));
                
                resolve();
                
            });

        });
    
    }

    private createHeaders(credentials: Credentials): Headers{

        let headers = new Headers();

        headers.append('Accept', 'application/json')
        headers.append("Authorization", "Basic " + btoa(credentials.username+ ':' + credentials.password)); 

        return headers;

    }

}