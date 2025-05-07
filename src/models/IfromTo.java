package models;

import java.awt.Component;

public interface IfromTo {
	/**
	 * Метод обработки "события" начало движения транзакции.
	 * Вызывается перед началом движения tr.  
	 * @param tr транзакция, которая уходит от об’єкта
	 */
	public void onOut(Transaction tr);

	/**
	 * Метод обработки "события" конец движения транзакции.
	 * Вызывается, когда tr приходит к об’єкту. 
	 * @param tr транзакция, которая пришла к об’єкту
	 */
	public void onIn(Transaction tr);

	/**
	 *Метод, предоставляющий доступ к компоненту, который представляет об’єкт на GUI
	 * @return ссылку на компонент, который отображает об’єкт на GUI
	 */
	public Component getComponent();

}
