package vista.componentes_vista;

final class HorizontalInfo extends DimensionInfo {
    HorizontalInfo(LayoutState layoutState, int gap) {
        super(layoutState, gap);
    }

    protected int getOriginalCell(GridConstraints constraints) {
        return constraints.getColumn();
    }

    protected int getOriginalSpan(GridConstraints constraints) {
        return constraints.getColSpan();
    }

    int getSizePolicy(int componentIndex) {
        return this.myLayoutState.getConstraints(componentIndex).getHSizePolicy();
    }

    int getChildLayoutCellCount(GridLayoutManager childLayout) {
        return childLayout.getColumnCount();
    }

    public int getMinimumWidth(int componentIndex) {
        return this.getMinimumSize(componentIndex).width;
    }

    public DimensionInfo getDimensionInfo(GridLayoutManager grid) {
        return grid.getHorizontalInfo();
    }

    public int getCellCount() {
        return this.myLayoutState.getColumnCount();
    }

    public int getPreferredWidth(int componentIndex) {
        return this.getPreferredSize(componentIndex).width;
    }
}