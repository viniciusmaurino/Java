SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `projeto_estoque` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `projeto_estoque` ;

-- -----------------------------------------------------
-- Table `projeto_estoque`.`local_estoque`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `projeto_estoque`.`local_estoque` (
  `codigo` VARCHAR(8) NOT NULL ,
  `descricao` TEXT NOT NULL ,
  PRIMARY KEY (`codigo`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto_estoque`.`categoria_produto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `projeto_estoque`.`categoria_produto` (
  `codigo` INT NOT NULL AUTO_INCREMENT ,
  `descricao` TEXT NOT NULL ,
  PRIMARY KEY (`codigo`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto_estoque`.`produto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `projeto_estoque`.`produto` (
  `codigo` INT NOT NULL AUTO_INCREMENT ,
  `descricao` TEXT NOT NULL ,
  `quantidade` INT NOT NULL ,
  `local_estoque_codigo` VARCHAR(8) NOT NULL ,
  `categoria_produto_codigo` INT NOT NULL ,
  PRIMARY KEY (`codigo`) ,
  INDEX `fk_produto_local_estoque_idx` (`local_estoque_codigo` ASC) ,
  INDEX `fk_produto_categoria_produto1_idx` (`categoria_produto_codigo` ASC) ,
  CONSTRAINT `fk_produto_local_estoque`
    FOREIGN KEY (`local_estoque_codigo` )
    REFERENCES `projeto_estoque`.`local_estoque` (`codigo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_produto_categoria_produto1`
    FOREIGN KEY (`categoria_produto_codigo` )
    REFERENCES `projeto_estoque`.`categoria_produto` (`codigo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto_estoque`.`usuario`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `projeto_estoque`.`usuario` (
  `matricula` VARCHAR(4) NOT NULL ,
  `senha` VARCHAR(8) NOT NULL ,
  `nivel_acesso` VARCHAR(15) NOT NULL ,
  PRIMARY KEY (`matricula`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto_estoque`.`fornecedor`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `projeto_estoque`.`fornecedor` (
  `cnpj` VARCHAR(18) NOT NULL ,
  `nome` VARCHAR(20) NOT NULL ,
  `razao_social` VARCHAR(45) NOT NULL ,
  `endereco` TEXT NOT NULL ,
  `email` VARCHAR(50) NOT NULL ,
  `telefone` VARCHAR(15) NOT NULL ,
  PRIMARY KEY (`cnpj`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto_estoque`.`nota_fiscal`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `projeto_estoque`.`nota_fiscal` (
  `numero` INT NOT NULL AUTO_INCREMENT ,
  `data` DATE NOT NULL ,
  `valor` FLOAT NOT NULL ,
  `status` VARCHAR(15) NOT NULL ,
  PRIMARY KEY (`numero`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `projeto_estoque`.`fornecedor_produto`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `projeto_estoque`.`fornecedor_produto` (
  `produto_codigo` INT NOT NULL ,
  `fornecedor_cnpj` VARCHAR(18) NOT NULL ,
  `nota_fiscal_numero` INT NOT NULL ,
  `quantidade` INT NOT NULL ,
  INDEX `fk_fornecedor_produto_produto1_idx` (`produto_codigo` ASC) ,
  INDEX `fk_fornecedor_produto_fornecedor1_idx` (`fornecedor_cnpj` ASC) ,
  INDEX `fk_fornecedor_produto_nota_fiscal1_idx` (`nota_fiscal_numero` ASC) ,
  CONSTRAINT `fk_fornecedor_produto_produto1`
    FOREIGN KEY (`produto_codigo` )
    REFERENCES `projeto_estoque`.`produto` (`codigo` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fornecedor_produto_fornecedor1`
    FOREIGN KEY (`fornecedor_cnpj` )
    REFERENCES `projeto_estoque`.`fornecedor` (`cnpj` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_fornecedor_produto_nota_fiscal1`
    FOREIGN KEY (`nota_fiscal_numero` )
    REFERENCES `projeto_estoque`.`nota_fiscal` (`numero` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `projeto_estoque` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
