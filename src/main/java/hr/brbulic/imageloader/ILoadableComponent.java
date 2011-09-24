package hr.brbulic.imageloader;

/**
 * Created by IntelliJ IDEA.
 * User: bbulic
 * Date: 21.09.11.
 * Time: 20:27
 * To change this template use File | Settings | File Templates.
 */
public interface ILoadableComponent {

    /**
     * Gets a component needed to be loaded
     *
     * @return
     */
    ILoadableImageDetails getLoadableComponent();
}
