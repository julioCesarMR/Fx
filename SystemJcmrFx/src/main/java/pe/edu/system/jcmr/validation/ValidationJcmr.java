/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.system.jcmr.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import javafx.scene.control.ComboBox;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;
import pe.edu.system.jcmr.utilCommon.ConstansRegx;
import pe.edu.system.jcmr.utilCommon.DateUtil;

/**
 *
 * @author Julio Cesar Meza Rios
 */
public class ValidationJcmr {



	public void onlyNumber(TextField text, int length) {

		text.setOnKeyTyped((KeyEvent keyEvent) -> {

			if (!keyEvent.getCharacter().matches(ConstansRegx.NUMEROS)) {

				keyEvent.consume();
			}
			if (text.getText().length() >= length) {
				keyEvent.consume();
			}

		});
	}

	public void numberAndLetters(TextField text, int length) {

		text.setOnKeyTyped((KeyEvent keyEvent) -> {

			if (!keyEvent.getCharacter().matches(ConstansRegx.LETRAS_NUMBERO)) {

				keyEvent.consume();
			}
			if (text.getText().length() >= length) {
				keyEvent.consume();
			}

		});
	}

	public void lettersAndSpaces(TextField text, int length) {
		text.setOnKeyTyped((KeyEvent keyEvent) -> {

			if (!keyEvent.getCharacter().matches(ConstansRegx.LETRAS)) {

				keyEvent.consume();
			}
			if (text.getText().length() >= length) {
				keyEvent.consume();
			}

		});

	}

	public void lengthCaracteres(TextField text, int length) {
		text.setOnKeyTyped((KeyEvent keyEvent) -> {
			if (text.getText().length() >= length) {
				keyEvent.consume();
			}

		});

	}


	public void validationEmail(TextField control, int length, ValidationSupport validate) {

		validate.registerValidator(control,
				Validator.createRegexValidator(null, ConstansRegx.EMAIL_PATTERN, Severity.ERROR));
		lengthCaracteres(control, length);

	}

	public void validationTelefono(TextField control, ValidationSupport validate) {

		validate.registerValidator(control,
				Validator.createRegexValidator(null, ConstansRegx.TELEFONO_SIETE_DIGITOS, Severity.ERROR));

	}

	public void validationCelular(TextField control, ValidationSupport validate) {

		validate.registerValidator(control,
				Validator.createRegexValidator(null, ConstansRegx.CELULAR_NUEVE_DIGITOS, Severity.ERROR));

	}

	public void validationApellido(TextField control, int length, ValidationSupport validate) {

		validate.registerValidator(control,
				Validator.createRegexValidator(null, ConstansRegx.APELLIDO, Severity.ERROR));
		lettersAndSpaces(control, length);
	}

	public void validationNombre(TextField control, int length, ValidationSupport validate) {

		validate.registerValidator(control, Validator.createRegexValidator(null, ConstansRegx.NOMBRES, Severity.ERROR));
		lettersAndSpaces(control, length);

	}

	public void validationDni(TextField control, int length, ValidationSupport validate) {

		validate.registerValidator(control, true, this::resultDni);
		onlyNumber(control, length);
	}

	public void validationCombox(ComboBox<?> control, ValidationSupport validate) {

		validate.registerValidator(control, true,Validator.createEmptyValidator(null, Severity.ERROR));

	}



	public void validationCombox(DatePicker control, ValidationSupport validate) {

		validate.registerValidator(control, false, (Control c,LocalDate newValue) ->
		ValidationResult.fromErrorIf(control, "",lessEge(newValue) == false));
	
	}

	public boolean lessEge(LocalDate value) {
		boolean rs = true;
		if (value != null) {

			if (DateUtil.differenceYear(value, LocalDate.now()) <= 17) {
				rs = false;
			}
		}
		return rs;
	}

	private ValidationResult resultDni(Control control, String text) {
		boolean condition = !text.matches(ConstansRegx.DNI_OCHO_DIGITOS) || text.equals("00000000") || text.isEmpty();
		return ValidationResult.fromMessageIf(control, null, Severity.ERROR,condition);
	}

	public void datePickerFormt(DatePicker picker,String regx) {
		picker.setConverter(new StringConverter<LocalDate>() {
			private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(regx);

			@Override
			public String toString(LocalDate localDate) {
				if (localDate == null)
					return "";
				return dateTimeFormatter.format(localDate);
			}

			@Override
			public LocalDate fromString(String dateString) {
				if (dateString == null || dateString.trim().isEmpty())
					return null;
				try {
					return LocalDate.parse(dateString, dateTimeFormatter);
				} catch (Exception e) {
					return null;
				}

			}
		});

	}


}
