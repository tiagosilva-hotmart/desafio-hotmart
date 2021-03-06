package br.com.hotmart.desafiohotmart.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.hotmart.desafiohotmart.builder.MessageBuilder;
import br.com.hotmart.desafiohotmart.enumerations.ReturnTypeEnum;
import br.com.hotmart.desafiohotmart.exception.ServiceException;
import br.com.hotmart.desafiohotmart.service.UsuarioService;
import br.com.hotmart.desafiohotmart.utils.SecurityUtils;
import br.com.hotmart.desafiohotmart.vo.ResponseVO;
import br.com.hotmart.desafiohotmart.vo.UsuarioVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Responsável por conter serviços de entrada de 
 * ligados a autenticação
 * 
 * @author Tiago
 *
 */
@RestController
@RequestMapping("auth")
public class AuthController {
	
   @Autowired
   private UsuarioService usuarioService;

  /**
   * Responsável por retornar o usuario logado na
   * aplicação.
   * 
   * @param user
   * @return
   */
  @ApiOperation(value = "Obtenção do usuário logado", nickname = "user", notes="Obtenção o usuário logado na aplicação")
  @ApiResponses({ 
      @ApiResponse(code = 200, message = "Success", response = ResponseVO.class)
  })
  @GetMapping("/user")
  public ResponseVO<UsuarioVO> user(Principal user) {  
	  
	  return new ResponseVO<>(ReturnTypeEnum.SUCESSO, SecurityUtils.getUsuarioVOByUserPrincipal(user));
    
  }
  
  /**
   * Responsável por registar um novo usuário.
   * 
   * @param usuarioVO
   * @return
   */
  @ApiOperation(value = "Registro de usuário", nickname = "register", notes="Responsável por registar/cadastar um novo usuário")
  @ApiResponses({ 
      @ApiResponse(code = 200, message = "Success", response = ResponseVO.class)
  })
  @PostMapping("/register")
  public ResponseVO<UsuarioVO> register(@Valid @RequestBody UsuarioVO usuarioVO){
	  
	  MessageBuilder<UsuarioVO> messageBuilder = new MessageBuilder<>();
	  
	  try{
		  
		  messageBuilder.setVoReturn(usuarioService.registerNewUserAccount(usuarioVO.toUsuario()).toUsuarioVO());  
		  
	  }catch(ServiceException serviceException){
		  
		  messageBuilder.addErrorByServiceException(serviceException);
		  
	  }  
	  
	  return messageBuilder.build();
	  
  }  

}
