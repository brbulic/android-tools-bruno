package hr.brbulic.mvc;

/**
 * User: bbulic
 * Date: 23.10.11.
 * Time: 15:26
 * Some hr.brbulic.mvc description
 */
public abstract class DataViewModel<TData> extends ViewModelBase{

    public abstract TData getData();

}
