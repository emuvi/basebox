package pin.basebox;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import pin.jarbox.dsk.ColPanel;
import pin.jarbox.dsk.Helm;
import pin.jarbox.dsk.HelmEdit;
import pin.jarbox.dsk.Icons;
import pin.jarbox.dsk.Panel;
import pin.jarbox.dsk.PopMenu;
import pin.jarbox.dsk.RowPanel;
import pin.jarbox.wzd.WzdDesk;
import pin.jarbox.wzd.WzdLog;


public class HelmMain extends Helm {

  private final JButton newButton = Icons.buttonNew(e -> menuNew());
  private final JButton openButton = Icons.buttonOpen(e -> menuOpen());
  private final JButton saveButton = Icons.buttonSave(e -> menuSave());
  private final RowPanel toolsPanel = new RowPanel(newButton, openButton, saveButton);
  private final DefaultListModel<Connector> connectorsModel = new DefaultListModel<>();
  private final JList<Connector> connectorsList = new JList<>(connectorsModel);
  private final JScrollPane connectorsScroll = new JScrollPane(connectorsList);
  private final JButton insertButton = Icons.buttonInsert(e -> menuInsert());
  private final JButton editButton = Icons.buttonEdit(e -> menuEdit());
  private final JButton deleteButton = Icons.buttonDelete(e -> menuDelete());
  private final JButton cogButton = Icons.buttonCog(e -> menuCog());
  private final PopMenu cogMenu = new PopMenu();
  private final ColPanel connectorsEdits = new ColPanel(insertButton, editButton,
      deleteButton, cogButton);
  private final Panel connectorsPanel = new RowPanel().addMax(connectorsScroll).add(
      connectorsEdits);

  public HelmMain() {
    super(new JFrame("BaseBox"), new ColPanel());
    setExitOnClose();
    setIcon(Icons.get(HelmMain.class, "basebox.png"));
    container.add(toolsPanel).addMax(connectorsPanel);
    initCogMenu();
    pack();
  }

  private void initCogMenu() {
    cogMenu.put("Export to CSV", e -> exportToCSV());
    cogMenu.put("Import from CSV", e -> importFromCSV());
  }

  private void menuNew() {
    if (WzdDesk.question("Do you wanna clear the connectors.")) {
      connectorsModel.clear();
    }
  }

  private void menuOpen() {}

  private void menuSave() {}

  private void menuInsert() {
    try {
      new HelmEdit<>(Connector.class, null, true, connector -> makeInsert(connector))
          .show();
    } catch (Exception e) {
      WzdLog.treat(e);
    }
  }

  private Boolean makeInsert(Connector connector) {
    connectorsModel.add(connectorsList.getSelectedIndex() + 1, connector);
    return true;
  }

  private void menuEdit() {
    try {
      Connector selected = connectorsList.getSelectedValue();
      if (selected != null) {
        new HelmEdit<>(Connector.class, selected, false, connector -> makeEdit(connector))
            .show();
      }
    } catch (Exception e) {
      WzdLog.treat(e);
    }
  }

  private Boolean makeEdit(Connector connector) {
    connectorsList.revalidate();
    return true;
  }

  private void menuDelete() {
    int selected = connectorsList.getSelectedIndex();
    if (selected > -1) {
      connectorsModel.remove(selected);
    }
  }

  private void menuCog() {
    cogMenu.show(cogButton);
  }

  private void exportToCSV() {
    System.out.println("Export");
  }

  private void importFromCSV() {
    System.out.println("Import");
  }
}
